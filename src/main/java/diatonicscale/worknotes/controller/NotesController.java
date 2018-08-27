/**
 * User: DiatonicScale
 * Date: 26.08.2018
 */

package diatonicscale.worknotes.controller;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.service.NotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NotesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    private NotesService service;

    // Categories
    Category addCategory(Category category) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Add category \"{}\" for user with id = {}", category.getName(), userId);
        return service.addCategory(category, userId);
    }

    void updateCategory(Category category) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Update category \"{}\" (id = {}) for user with id = {}", category.getName(), category.getId(), userId);
        service.updateCategory(category, userId);
    }

    Category getCategory(int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Get category with id = {} for user with id = {}", categoryId, userId);
        return service.getCategory(categoryId, userId);
    }

    void deleteCategory(int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Delete category with id = {} for user with id = {}", categoryId, userId);
        service.deleteCategory(categoryId, userId);
    }

    List<Category> getUserCategories() {
        int userId = LoggedInUser.getId();
        LOGGER.info("Get categories for user with id = {}", userId);
        return service.getUserCategories(userId);
    }

    // Notes
    Note addNote(Note note, int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Add note \"{}\" for user with id = {}", note.getName(), userId);
        return service.addNote(note, categoryId, userId);
    }

    void updateNote(Note note, int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Update note \"{}\" (id = {}) for user with id = {}", note.getName(), note.getId(), userId);
        service.updateNote(note, categoryId, userId);
    }

    void deleteNote(int noteId, int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Delete note with id = {} for user with id = {}", noteId, userId);
        service.deleteNote(noteId, categoryId, userId);
    }

    void deleteCategoryNotes(int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Delete notes from category with id = {} for user with id = {}", categoryId, userId);
        service.deleteCategoryNotes(categoryId, userId);
    }

    List<Note> getCategoryNotes(int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Get notes from category with id = {} for user with id = {}", categoryId, userId);
        return service.getCategoryNotes(categoryId, userId);
    }

    List<Note> getUserNotes() {
        int userId = LoggedInUser.getId();
        LOGGER.info("Get all notes for user with id = {}", userId);
        return service.getUserNotes(userId);
    }

    Note getNote(int noteId, int categoryId) {
        int userId = LoggedInUser.getId();
        LOGGER.info("Get note with id = {} from category with id = {} for user with id = {}", noteId, categoryId, userId);
        return service.getNote(noteId, categoryId, userId);
    }
}
