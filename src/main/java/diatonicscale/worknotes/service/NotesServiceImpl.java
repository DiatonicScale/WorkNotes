/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;

import java.util.List;

public class NotesServiceImpl implements NotesService {
    @Override
    public Category addCategory(Category category, int userId) {
        return null;
    }

    @Override
    public void updateCategory(Category category, int userId) {

    }

    @Override
    public void deleteCategory(int categoryId, int userId) {

    }

    @Override
    public List<Category> getUserCategories(int userId) {
        return null;
    }

    @Override
    public Note addNote(Note note, int categoryId, int userId) {
        return null;
    }

    @Override
    public void updateNote(Note note, int categoryId, int userId) {

    }

    @Override
    public void deleteNote(int noteId, int categoryId, int userId) {

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
