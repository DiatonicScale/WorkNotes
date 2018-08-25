/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.model.Source;

import java.util.List;

public interface NotesService {
    // Categories
    Category addCategory(Category category, int userId);

    void updateCategory(Category category, int userId);

    void deleteCategory(int categoryId, int userId);

    List<Category> getUserCategories(int userId);

    // Notes
    Note addNote(Note note, int categoryId, int userId);

    void updateNote(Note note, int categoryId, int userId);

    void deleteNote(int noteId, int categoryId, int userId);

    void deleteCategoryNotes(int categoryId, int userId);

    List<Note> getCategoryNotes(int categoryId, int userId);

    List<Note> getUserNotes(int userId); // show all user notes

    Note getNote(int noteId, int categoryId, int userId);


    // TODO:
    // List<Note> getBySource(Source source);
    // List<Category> getUserCategoriesWithNotes(int userId); // lazy fetching of the List<Note> in Category needed?
}
