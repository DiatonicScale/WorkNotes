/**
 * User: DiatonicScale
 * Date: 02.09.2018
 */

package diatonicscale.worknotes.repository.springjdbc;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SpringJdbcNotesRepository implements NotesRepository {

    private static final RowMapper<Note> NOTE_MAPPER = new RowMapper<Note>() {
        @Override
        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Note(rs.getInt("id"),
                    rs.getInt("category_id"),
                    rs.getString("name"),
                    rs.getTimestamp("creation_time").toLocalDateTime(),
                    rs.getTimestamp("last_edit_time").toLocalDateTime(),
                    rs.getString("value"));
        }
    };
    private static final RowMapper<Category> CATEGORY_MAPPER = new RowMapper<Category>() {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Category(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getTimestamp("creation_time").toLocalDateTime(),
                    rs.getTimestamp("last_edit_time").toLocalDateTime());
        }
    };
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert simpleCategoryInsert;
    private SimpleJdbcInsert simpleNoteInsert;


    @Autowired
    public SpringJdbcNotesRepository(DataSource dataSource) {
        this.simpleCategoryInsert = new SimpleJdbcInsert(dataSource).withTableName("categories").usingGeneratedKeyColumns("id", "creation_time", "last_edit_time");
        this.simpleNoteInsert = new SimpleJdbcInsert(dataSource).withTableName("notes").usingGeneratedKeyColumns("id", "creation_time", "last_edit_time");
    }

    @Override
    public Category saveCategory(Category category, int userId) {
        MapSqlParameterSource categoryMap = new MapSqlParameterSource();
        categoryMap.addValue("id", category.getId())
                   .addValue("user_id", userId)
                   .addValue("name", category.getName());

        if (category.getId() == null) {
            Number categoryId = (Number)simpleCategoryInsert.executeAndReturnKeyHolder(categoryMap).getKeys().get("id");
            category.setId(categoryId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE categories " +
                                                  "SET name=:name, last_edit_time=now() " +
                                                  "WHERE id=:id AND user_id=:user_id", categoryMap) == 0) {
                return null;
            }
        }
        return category;
    }

    @Override
    public Category getCategory(int categoryId, int userId) {
        return jdbcTemplate.queryForObject("SELECT * " +
                                           "FROM categories " +
                                           "WHERE id=? AND user_id=?", CATEGORY_MAPPER, categoryId, userId);
    }

    @Override
    public boolean deleteCategory(int categoryId, int userId) {
        return jdbcTemplate.update("DELETE FROM categories " +
                                   "WHERE user_id=? AND id=?", userId, categoryId) != 0;
    }

    @Override
    public List<Category> getUserCategories(int userId) {
        return jdbcTemplate.query("SELECT * " +
                                  "FROM categories " +
                                  "WHERE user_id=? ORDER BY last_edit_time DESC", CATEGORY_MAPPER, userId);
    }

    @Override
    public Note saveNote(Note note, int categoryId, int userId) {
        MapSqlParameterSource noteMap = new MapSqlParameterSource();
        noteMap.addValue("id", note.getId())
                .addValue("category_id", categoryId)
                .addValue("name", note.getName())
                .addValue("value", note.getValue())
                .addValue("user_id", userId);

        if (note.getId() == null) {
            Number noteId = (Number)simpleNoteInsert.executeAndReturnKeyHolder(noteMap).getKeys().get("id");
            note.setId(noteId.intValue());
        } else {

            int rowsAffected = namedParameterJdbcTemplate.update("UPDATE notes " +
                                                                 "SET name=:name, value=:value, last_edit_time=now() " +
                                                                 "WHERE id=:id AND category_id=:category_id AND category_id IN (SELECT id " +
                                                                                                                                "FROM categories " +
                                                                                                                                "WHERE user_id=:user_id)", noteMap);
            if (rowsAffected == 0)
                return null;

        }
        return note;
    }

    @Override
    public boolean deleteNote(int noteId, int userId) {
        return jdbcTemplate.update("DELETE FROM notes " +
                                   "WHERE id=? AND category_id IN (SELECT id " +
                                                                  "FROM categories " +
                                                                  "WHERE user_id=?)", noteId, userId) != 0;
    }

    @Override
    public boolean deleteCategoryNotes(int categoryId, int userId) {
        return jdbcTemplate.update("DELETE FROM notes " +
                                   "WHERE category_id=? AND category_id IN (SELECT id " +
                                                                           "FROM categories " +
                                                                           "WHERE user_id=?)", categoryId, userId) != 0;
    }

    @Override
    public List<Note> getCategoryNotes(int categoryId, int userId) {
        return jdbcTemplate.query("SELECT * " +
                                  "FROM notes " +
                                  "WHERE category_id=? AND category_id IN (SELECT id " +
                                                                          "FROM categories " +
                                                                          "WHERE user_id=?)", NOTE_MAPPER, categoryId, userId);
    }

    @Override
    public List<Note> getUserNotes(int userId) {
        return jdbcTemplate.query("SELECT * " +
                                  "FROM notes " +
                                  "WHERE category_id IN (SELECT id " +
                                                        "FROM categories " +
                                                        "WHERE user_id=?)", NOTE_MAPPER, userId);
    }

    @Override
    public Note getNote(int noteId, int userId) {
        return jdbcTemplate.queryForObject("SELECT * " +
                                           "FROM notes " +
                                           "WHERE id=? AND category_id IN (SELECT id " +
                                                                           "FROM categories " +
                                                                           "WHERE user_id=?)", NOTE_MAPPER, noteId, userId);
    }
}
