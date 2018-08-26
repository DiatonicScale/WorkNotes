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

    List<Category> getUserCategories(int userId); // EmptyList if not found

    // Notes
    Note saveNote(Note note, int categoryId, int userId);

    boolean deleteNote(int noteId, int categoryId, int userId);

    boolean deleteCategoryNotes(int categoryId, int userId);

    List<Note> getCategoryNotes(int categoryId, int userId); // EmptyList if not found

    List<Note> getUserNotes(int userId); // EmptyList if not found

    Note getNote(int noteId, int categoryId, int userId);
}
