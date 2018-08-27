/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.exception.NotesServiceException;
import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.repository.NotesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesServiceImpl.class);

    @Autowired
    private NotesRepository repository;

    @Override
    public Category addCategory(Category category, int userId) {
        Category addedCategory = repository.saveCategory(category, userId);
        if (addedCategory == null) {
            LOGGER.error("Category not added");
            throw new NotesServiceException("Can't add category");
        }
        return addedCategory;
    }

    @Override
    public void updateCategory(Category category, int userId) {
        if (repository.saveCategory(category, userId) == null) {
            LOGGER.error("Category not updated, id = " + category.getId());
            throw new NotesServiceException("Can't update category, id = " + category.getId());
        }
    }

    @Override
    public Category getCategory(int categoryId, int userId) {
        Category category = repository.getCategory(categoryId, userId);
        if (category == null) {
            LOGGER.error("Category with id = " + categoryId + "not found");
            throw new NotesServiceException("Can't find category with id = " + categoryId);
        }
        return category;
    }

    @Override
    public void deleteCategory(int categoryId, int userId) {
        if (!repository.deleteCategory(categoryId, userId)) {
            LOGGER.error("Category not deleted, id = " + categoryId);
            throw new NotesServiceException("Can't delete category, id = " + categoryId);
        }
    }

    @Override
    public List<Category> getUserCategories(int userId) {
        return repository.getUserCategories(userId);
    }

    @Override
    public Note addNote(Note note, int categoryId, int userId) {
        Note addedNote = repository.saveNote(note, categoryId, userId);
        if (addedNote == null) {
            LOGGER.error("Note not added");
            throw new NotesServiceException("Can't add note");
        }
        return addedNote;
    }

    @Override
    public void updateNote(Note note, int categoryId, int userId) {
        if (repository.saveNote(note, categoryId, userId) == null) {
            LOGGER.error("Note not updated, id = " + note.getId());
            throw new NotesServiceException("Can't update note, id = " + note.getId());
        }
    }

    @Override
    public void deleteNote(int noteId, int categoryId, int userId) {
        if (!repository.deleteNote(noteId, categoryId, userId)) {
            LOGGER.error("Note not deleted, id = " + noteId);
            throw new NotesServiceException("Can't delete note, id = " + noteId);
        }
    }

    @Override
    public void deleteCategoryNotes(int categoryId, int userId) {
        if (!repository.deleteCategoryNotes(categoryId, userId)) {
            LOGGER.error("Notes from category with id = " + categoryId + " not deleted");
            throw new NotesServiceException("Can't delete notes from category with id = " + categoryId);
        }
    }

    @Override
    public List<Note> getCategoryNotes(int categoryId, int userId) {
        return repository.getCategoryNotes(categoryId, userId);
    }

    @Override
    public List<Note> getUserNotes(int userId) {
        return repository.getUserNotes(userId);
    }

    @Override
    public Note getNote(int noteId, int categoryId, int userId) {
        Note note = repository.getNote(noteId, categoryId, userId);
        if (note == null) {
            LOGGER.error("Note with id = " + noteId + " from category with id = " + categoryId + " not found");
            throw new NotesServiceException("Can't find note with id = " + noteId + " from category with id = " + categoryId);
        }
        return note;
    }
}
