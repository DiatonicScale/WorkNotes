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
    Category addCategory(Category category, int userId);

    Note addNote(Note note, int userId);

    void deleteCategory(int categoryId); // throws exception?

    void deleteNote(int noteId); // throws exception?

    void deleteCategoryNotes(int categoryId); // throws exception?

    List<Category> getUserCategories(); // throws exception?

    //List<Category> getCategories(int parentId); // throws exception?, userId gets inside method and uses to call rep

    List<Note> getCategoryNotes(int categoryId); // throws exception?

    //List<CategoryWithNotes> getCategory(int parentId);

    void updateCategory(Category category);

    void updateNote(Note note);

    List<Note> getBySource(Source source); // throws exception?
}
