/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.model.Source;

import java.sql.SQLException;
import java.util.List;

public interface NotesService {
    // Categories
    Category addCategory(Category category, int userId) throws SQLException;

    void updateCategory(Category category, int userId) throws SQLException;

    Category getCategory(int categoryId, int userId) throws SQLException;

    void deleteCategory(int categoryId, int userId) throws SQLException;

    List<Category> getUserCategories(int userId) throws SQLException;

    // Notes
    Note addNote(Note note, int categoryId, int userId) throws SQLException;

    void updateNote(Note note, int categoryId, int userId) throws SQLException;

    void deleteNote(int noteId, int userId) throws SQLException;

    void deleteCategoryNotes(int categoryId, int userId) throws SQLException;

    List<Note> getCategoryNotes(int categoryId, int userId) throws SQLException;

    List<Note> getUserNotes(int userId) throws SQLException; // show all user notes

    Note getNote(int noteId, int userId) throws SQLException;


    // TODO:
    // List<Note> getBySource(Source source);
    // List<Category> getUserCategoriesWithNotes(int userId); // lazy fetching of the List<Note> in Category needed?
}
