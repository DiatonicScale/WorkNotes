package diatonicscale.worknotes.service;

import diatonicscale.worknotes.exception.NotesServiceException;
import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@ContextConfiguration({"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql")
public class NotesServiceImplTest {

    private static int USER_0_ID = 10000;
    private static int USER_1_ID = 10001;

    private static Category CATEGORY_1 = new Category(10004, USER_0_ID, "Maven", LocalDateTime.of(2018, 8, 31, 13, 0, 0), LocalDateTime.of(2018, 8, 31, 13, 0, 0));
    private static Category CATEGORY_2 = new Category(10002, USER_0_ID, "Java", LocalDateTime.of(2018, 8, 30, 10, 0, 0), LocalDateTime.of(2018, 8, 30, 10, 0, 0));
    private static Category CATEGORY_3 = new Category(10003, USER_0_ID, "Git", LocalDateTime.of(2018, 8, 29, 20, 0, 0), LocalDateTime.of(2018, 8, 29, 20, 0, 0));

    private static Note NOTE_1 = new Note(10007, CATEGORY_2.getId(), "Final keyword", LocalDateTime.of(2018, 8, 30, 11, 30, 0), LocalDateTime.of(2018, 8, 30, 11, 30, 0), "value final");
    private static Note NOTE_2 = new Note(10008, CATEGORY_3.getId(), "Clone command", LocalDateTime.of(2018, 8, 31, 23, 12, 0), LocalDateTime.of(2018, 8, 31, 23, 12, 0), "value clone");
    private static Note NOTE_3 = new Note(10009, CATEGORY_3.getId(), "Commit", LocalDateTime.of(2018, 9, 1, 11, 0, 0), LocalDateTime.of(2018, 9, 1, 11, 0, 0), "value commit");
    private static Note NOTE_4 = new Note(10010, CATEGORY_3.getId(), "Push", LocalDateTime.of(2018, 9, 2, 20, 00, 0), LocalDateTime.of(2018, 9, 2, 20, 00, 0), "value push");
    private static Note NOTE_5 = new Note(10011, CATEGORY_2.getId(), "Remote debug", LocalDateTime.of(2018, 8, 30, 10, 0, 0), LocalDateTime.of(2018, 8, 30, 10, 0, 0), "value remote");
    private static Note NOTE_6 = new Note(10012, CATEGORY_1.getId(), "Dependencies", LocalDateTime.of(2018, 8, 31, 7, 20, 0), LocalDateTime.of(2018, 8, 31, 7, 20, 0), "value depend");
    private static Note NOTE_7 = new Note(10013, CATEGORY_2.getId(), "Servlets", LocalDateTime.of(2018, 8, 30, 1, 02, 0), LocalDateTime.of(2018, 8, 30, 1, 02, 0), "value servlet");
    private static Note NOTE_8 = new Note(10014, CATEGORY_1.getId(), "Skip tests mode", LocalDateTime.of(2018, 8, 31, 17, 00, 0), LocalDateTime.of(2018, 8, 31, 17, 00, 0), "value skip test");

    @Autowired
    private NotesService notesService;

    @Test
    public void addCategory() throws Exception {
        Category addedCategory = new Category(USER_0_ID, "TEST CATEGORY", null, null);
        addedCategory = notesService.addCategory(addedCategory, USER_0_ID);
        Assert.assertEquals(Arrays.asList(addedCategory, CATEGORY_1, CATEGORY_2, CATEGORY_3), notesService.getUserCategories(USER_0_ID));
    }

    @Test
    public void updateCategory() throws Exception {
        Category categoryToUpdate = new Category(CATEGORY_3.getId(), USER_0_ID, "Updated category name");
        notesService.updateCategory(categoryToUpdate, USER_0_ID);
        Category takenCategory = notesService.getCategory(CATEGORY_3.getId(), USER_0_ID);
        Assert.assertEquals(categoryToUpdate, takenCategory);
        Assert.assertEquals(categoryToUpdate.getName(), takenCategory.getName());
    }

    @Test
    public void getCategory() throws Exception {
        Assert.assertEquals(CATEGORY_2, notesService.getCategory(CATEGORY_2.getId(), USER_0_ID));
    }

    @Test(expected = NotesServiceException.class)
    public void testCategoryGetNotFound() throws Exception {
        notesService.getCategory(9999, USER_0_ID);
    }

    @Test
    public void deleteCategory() throws Exception {
        notesService.deleteCategory(CATEGORY_2.getId(), USER_0_ID);
        Assert.assertEquals(Arrays.asList(CATEGORY_1, CATEGORY_3), notesService.getUserCategories(USER_0_ID));
    }

    @Test(expected = NotesServiceException.class)
    public void testCategoryDeleteNotFound() throws Exception {
        notesService.deleteCategory(9999, USER_0_ID);
    }

    @Test
    public void getUserCategories() throws Exception {
        Assert.assertEquals(Arrays.asList(CATEGORY_1, CATEGORY_2, CATEGORY_3), notesService.getUserCategories(USER_0_ID));
    }

    @Test
    public void addNote() throws Exception {
        Note addedNote = new Note(CATEGORY_1.getId(), "TEST", null, null, "test value");
        addedNote = notesService.addNote(addedNote, CATEGORY_1.getId(), USER_0_ID);
        Assert.assertEquals(Arrays.asList(addedNote, NOTE_8, NOTE_6), notesService.getCategoryNotes(CATEGORY_1.getId(), USER_0_ID));
        Assert.assertEquals(Arrays.asList(addedNote, NOTE_4, NOTE_3, NOTE_2, NOTE_8, NOTE_6, NOTE_1, NOTE_5, NOTE_7), notesService.getUserNotes(USER_0_ID));
    }

    @Test
    public void updateNote() throws Exception {
        Note noteToUpdate = new Note(NOTE_3.getId(), CATEGORY_3.getId(), "Updated name", "Updated value");
        notesService.updateNote(noteToUpdate, CATEGORY_3.getId(), USER_0_ID);
        Note takenNote = notesService.getNote(NOTE_3.getId(), USER_0_ID);
        Assert.assertEquals(noteToUpdate, takenNote);
        Assert.assertEquals(noteToUpdate.getName(), takenNote.getName());
        Assert.assertEquals(noteToUpdate.getValue(), takenNote.getValue());
    }

    @Test
    public void deleteNote() throws Exception {
        notesService.deleteNote(NOTE_4.getId(), USER_0_ID);
        Assert.assertEquals(Arrays.asList(NOTE_3, NOTE_2), notesService.getCategoryNotes(CATEGORY_3.getId(), USER_0_ID));
        Assert.assertEquals(Arrays.asList(NOTE_3, NOTE_2, NOTE_8, NOTE_6, NOTE_1, NOTE_5, NOTE_7), notesService.getUserNotes(USER_0_ID));
    }

    @Test(expected = NotesServiceException.class)
    public void testDeleteNoteNotFound() throws Exception {
        notesService.deleteNote(3456, USER_0_ID);
    }

    @Test
    public void deleteCategoryNotes() throws Exception {
        notesService.deleteCategoryNotes(CATEGORY_3.getId(), USER_0_ID);
        Assert.assertEquals(Collections.emptyList(), notesService.getCategoryNotes(CATEGORY_3.getId(), USER_0_ID));
        Assert.assertEquals(Arrays.asList(NOTE_8, NOTE_6, NOTE_1, NOTE_5, NOTE_7), notesService.getUserNotes(USER_0_ID));
    }

    @Test(expected = NotesServiceException.class)
    public void testDeleteCategoryNotesNotFound() throws Exception {
        notesService.deleteCategoryNotes(3468, USER_0_ID);
    }

    @Test
    public void getCategoryNotes() throws Exception {
        Assert.assertEquals(Arrays.asList(NOTE_1, NOTE_5, NOTE_7), notesService.getCategoryNotes(CATEGORY_2.getId(), USER_0_ID));
    }

    @Test
    public void getUserNotes() throws Exception {
        Assert.assertEquals(Arrays.asList(NOTE_4, NOTE_3, NOTE_2, NOTE_8, NOTE_6, NOTE_1, NOTE_5, NOTE_7), notesService.getUserNotes(USER_0_ID));
    }

    @Test
    public void getNote() throws Exception {
        Assert.assertEquals(NOTE_5, notesService.getNote(NOTE_5.getId(), USER_0_ID));
        Assert.assertEquals(NOTE_7, notesService.getNote(NOTE_7.getId(), USER_0_ID));
        Assert.assertEquals(NOTE_1, notesService.getNote(NOTE_1.getId(), USER_0_ID));
    }

    @Test(expected = NotesServiceException.class)
    public void testGetNoteNotFound() throws Exception {
        notesService.getNote(567, USER_0_ID);
    }
}