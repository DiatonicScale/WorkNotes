/**
 * User: DiatonicScale
 * Date: 30.08.2018
 */

package diatonicscale.worknotes.repository.jdbc;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcNotesRepository implements NotesRepository {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public Category saveCategory(Category category, int userId) throws SQLException {
        try (Connection conn = connectionFactory.getConnection();) {
            if (category.getId() == null) {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO categories (user_id, name) " +
                                                                  "VALUES (?, ?)");) {
                    st.setInt(1, userId);
                    st.setString(2, category.getName());
                    if (st.executeUpdate() != 0) {
                        return category;
                    }
                }
            } else {
                try (PreparedStatement st = conn.prepareStatement("UPDATE categories SET name=?, last_edit_time=now() " +
                                                                  "WHERE user_id=? AND id=?");) {
                    st.setString(1, category.getName());
                    st.setInt(2, userId);
                    st.setInt(3, category.getId());
                    if (st.executeUpdate() != 0) {
                        return category;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Category getCategory(int categoryId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * " +
                                                              "FROM categories " +
                                                              "WHERE id=? AND user_id=?");) {
                st.setInt(1, categoryId);
                st.setInt(2, userId);
                ResultSet result = st.executeQuery();
                if (!result.next())
                    return null;
                return new Category(result.getInt("id"), result.getInt("user_id"),
                        result.getString("name"), result.getTimestamp("creation_time").toLocalDateTime(),
                        result.getTimestamp("last_edit_time").toLocalDateTime());
            }
    }

    @Override
    public boolean deleteCategory(int categoryId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("DELETE FROM categories " +
                                                              "WHERE id=? AND user_id=?");) {
                st.setInt(1, categoryId);
                st.setInt(2, userId);
                if (st.executeUpdate() != 0) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public List<Category> getUserCategories(int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * " +
                                                              "FROM categories " +
                                                              "WHERE user_id=?");) {
                st.setInt(1, userId);
                ResultSet result = st.executeQuery();
                List<Category> userCategories = new ArrayList<>();
                while (result.next()) {
                    userCategories.add(new Category(result.getInt("id"), result.getInt("user_id"),
                            result.getString("name"), result.getTimestamp("creation_time").toLocalDateTime(),
                            result.getTimestamp("last_edit_time").toLocalDateTime()));
                }
                return userCategories;
            }
    }

    @Override
    public Note saveNote(Note note, int categoryId, int userId) throws SQLException {
        try (Connection conn = connectionFactory.getConnection();) {
            if (!isUsersCategory(conn, categoryId, userId))
                return null;
            if (note.getId() == null) {
                try (PreparedStatement st = conn.prepareStatement("INSERT INTO notes (category_id, name, value) " +
                                                                  "VALUES (?, ?, ?)");) {
                    st.setInt(1, categoryId);
                    st.setString(2, note.getName());
                    st.setString(3, note.getValue());
                    if (st.executeUpdate() != 0) {
                        return note;
                    }
                }
            } else {
                try (PreparedStatement st = conn.prepareStatement("UPDATE notes SET name=?, value=?, last_edit_time=now() " +
                                                                  "WHERE category_id=? AND id=?");) {
                    st.setString(1, note.getName());
                    st.setString(2, note.getValue());
                    st.setInt(3, categoryId);
                    st.setInt(4, note.getId());
                    if (st.executeUpdate() != 0) {
                        return note;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteNote(int noteId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("DELETE FROM notes " +
                                                              "WHERE id=? AND category_id IN (SELECT id " +
                                                                                             "FROM categories " +
                                                                                             "WHERE user_id=?)");) {
                st.setInt(1, noteId);
                st.setInt(2, userId);
                if (st.executeUpdate() != 0) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public boolean deleteCategoryNotes(int categoryId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("DELETE FROM notes " +
                                                              "WHERE category_id=? AND category_id IN (SELECT id " +
                                                                                                      "FROM categories " +
                                                                                                      "WHERE user_id=?)");) {
                st.setInt(1, categoryId);
                st.setInt(2, userId);
                if (st.executeUpdate() != 0) {
                    return true;
                }
            }
        return false;
    }

    @Override
    public List<Note> getCategoryNotes(int categoryId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * " +
                                                              "FROM notes " +
                                                              "WHERE category_id=? AND category_id IN (SELECT id " +
                                                                                                      "FROM categories " +
                                                                                                      "WHERE user_id=?)");) {
                st.setInt(1, categoryId);
                st.setInt(2, userId);
                ResultSet resultSet = st.executeQuery();
                List<Note> noteList = new ArrayList<>();
                while (resultSet.next()) {
                    noteList.add(new Note(resultSet.getInt("id"), resultSet.getInt("category_id"),
                            resultSet.getString("name"), resultSet.getTimestamp("creation_time").toLocalDateTime(),
                            resultSet.getTimestamp("last_edit_time").toLocalDateTime(), resultSet.getString("value")));
                }
                return noteList;
            }
    }

    @Override
    public List<Note> getUserNotes(int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * " +
                                                              "FROM notes " +
                                                              "WHERE category_id IN (SELECT id " +
                                                                                    "FROM categories " +
                                                                                    "WHERE user_id=?)");) {
                st.setInt(1, userId);
                ResultSet resultSet = st.executeQuery();
                List<Note> noteList = new ArrayList<>();
                while (resultSet.next())
                    noteList.add(new Note(resultSet.getInt("id"), resultSet.getInt("category_id"),
                            resultSet.getString("name"), resultSet.getTimestamp("creation_time").toLocalDateTime(),
                            resultSet.getTimestamp("last_edit_time").toLocalDateTime(), resultSet.getString("value")));
                return noteList;
            }
    }

    @Override
    public Note getNote(int noteId, int userId) throws SQLException {
            try (Connection conn = connectionFactory.getConnection();
                 PreparedStatement st = conn.prepareStatement("SELECT * " +
                                                              "FROM notes " +
                                                              "WHERE category_id IN (SELECT id " +
                                                                                    "FROM categories " +
                                                                                    "WHERE user_id=?) " +
                                                                                    "AND id=?");) {
                st.setInt(1, userId);
                st.setInt(2, noteId);
                ResultSet resultSet = st.executeQuery();
                if (!resultSet.next())
                    return null;
                return new Note(resultSet.getInt("id"), resultSet.getInt("category_id"),
                        resultSet.getString("name"), resultSet.getTimestamp("creation_time").toLocalDateTime(),
                        resultSet.getTimestamp("last_edit_time").toLocalDateTime(), resultSet.getString("value"));
            }
    }

    private boolean isUsersCategory(Connection conn, int categoryId, int userId) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("SELECT id " +
                                                          "FROM categories " +
                                                          "WHERE categories.id=? AND categories.user_id=?");) {
            st.setInt(1, categoryId);
            st.setInt(2, userId);
            ResultSet resultSet = st.executeQuery();
            if (!resultSet.next())
                return false;
        }
        return true;
    }
}
