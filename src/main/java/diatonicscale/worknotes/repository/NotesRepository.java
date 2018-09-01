/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.repository;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;

import java.sql.SQLException;
import java.util.List;

public interface NotesRepository {
    // Categories
    Category saveCategory(Category category, int userId) throws SQLException;

    Category getCategory(int categoryId, int userId) throws SQLException;

    boolean deleteCategory(int categoryId, int userId) throws SQLException;

    List<Category> getUserCategories(int userId) throws SQLException; // EmptyList if not found

    // Notes
    Note saveNote(Note note, int categoryId, int userId) throws SQLException;

    boolean deleteNote(int noteId, int userId) throws SQLException;

    boolean deleteCategoryNotes(int categoryId, int userId) throws SQLException;

    List<Note> getCategoryNotes(int categoryId, int userId) throws SQLException; // EmptyList if not found

    List<Note> getUserNotes(int userId) throws SQLException; // EmptyList if not found

    Note getNote(int noteId, int userId) throws SQLException;
}
