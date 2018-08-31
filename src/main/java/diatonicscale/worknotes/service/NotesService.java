/**
 * User: DiatonicScale
 * Date: 14.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.exception.RepositoryException;
import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.model.Source;

import java.util.List;

public interface NotesService {
    // Categories
    Category addCategory(Category category, int userId) throws RepositoryException;

    void updateCategory(Category category, int userId) throws RepositoryException;

    Category getCategory(int categoryId, int userId) throws RepositoryException;

    void deleteCategory(int categoryId, int userId) throws RepositoryException;

    List<Category> getUserCategories(int userId) throws RepositoryException;

    // Notes
    Note addNote(Note note, int categoryId, int userId) throws RepositoryException;

    void updateNote(Note note, int categoryId, int userId) throws RepositoryException;

    void deleteNote(int noteId, int userId) throws RepositoryException;

    void deleteCategoryNotes(int categoryId, int userId) throws RepositoryException;

    List<Note> getCategoryNotes(int categoryId, int userId) throws RepositoryException;

    List<Note> getUserNotes(int userId) throws RepositoryException; // show all user notes

    Note getNote(int noteId, int userId) throws RepositoryException;


    // TODO:
    // List<Note> getBySource(Source source);
    // List<Category> getUserCategoriesWithNotes(int userId); // lazy fetching of the List<Note> in Category needed?
}
