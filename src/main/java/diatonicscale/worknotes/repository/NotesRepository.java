/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.repository;

import diatonicscale.worknotes.exception.RepositoryException;
import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;

import java.sql.SQLException;
import java.util.List;

public interface NotesRepository {
    // Categories
    Category saveCategory(Category category, int userId) throws RepositoryException;

    Category getCategory(int categoryId, int userId) throws RepositoryException;

    boolean deleteCategory(int categoryId, int userId) throws RepositoryException;

    List<Category> getUserCategories(int userId) throws RepositoryException; // EmptyList if not found

    // Notes
    Note saveNote(Note note, int categoryId, int userId) throws RepositoryException;

    boolean deleteNote(int noteId, int userId) throws RepositoryException;

    boolean deleteCategoryNotes(int categoryId, int userId) throws RepositoryException;

    List<Note> getCategoryNotes(int categoryId, int userId) throws RepositoryException; // EmptyList if not found

    List<Note> getUserNotes(int userId) throws RepositoryException; // EmptyList if not found

    Note getNote(int noteId, int userId) throws RepositoryException;
}
