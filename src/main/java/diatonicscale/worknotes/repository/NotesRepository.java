/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.repository;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;

import java.util.List;

public interface NotesRepository {
    // Categories
    Category saveCategory(Category category, int userId);

    boolean deleteCategory(int categoryId, int userId);

    List<Category> getUserCategories(int userId);

    // Notes
    Note saveNote(Note note, int categoryId, int userId);

    boolean deleteNote(int noteId, int categoryId, int userId);

    void deleteCategoryNotes(int categoryId, int userId);

    List<Note> getCategoryNotes(int categoryId, int userId);

    List<Note> getUserNotes(int userId);

    Note getNote(int noteId, int categoryId, int userId);
}
