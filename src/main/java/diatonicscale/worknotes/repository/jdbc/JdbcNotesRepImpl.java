/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.repository.jdbc;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.repository.NotesRepository;

import java.util.List;

public class JdbcNotesRepImpl implements NotesRepository {
    @Override
    public Category saveCategory(Category category, int userId) {
        return null;
    }

    @Override
    public boolean deleteCategory(int categoryId, int userId) {
        return false;
    }

    @Override
    public List<Category> getUserCategories(int userId) {
        return null;
    }

    @Override
    public Note saveNote(Note note, int categoryId, int userId) {
        return null;
    }

    @Override
    public boolean deleteNote(int noteId, int categoryId, int userId) {
        return false;
    }

    @Override
    public void deleteCategoryNotes(int categoryId, int userId) {

    }

    @Override
    public List<Note> getCategoryNotes(int categoryId, int userId) {
        return null;
    }

    @Override
    public List<Note> getUserNotes(int userId) {
        return null;
    }

    @Override
    public Note getNote(int noteId, int categoryId, int userId) {
        return null;
    }
}
