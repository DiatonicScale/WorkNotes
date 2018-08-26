/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.repository.mock;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import diatonicscale.worknotes.repository.NotesRepository;
import diatonicscale.worknotes.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MockNotesRepository implements NotesRepository {
    private static final int USER_ID_ONE = 1;
    private static final int USER_ID_TWO = 2;


    // userId -> (categoryId -> category)
    private Map<Integer, Map<Integer, Category>> userCategories = new ConcurrentHashMap<>();
    // categoryId -> (noteId -> Note)
    private Map<Integer, Map<Integer, Note>> categoryNotes = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        saveCategory(new Category(USER_ID_ONE, "Java", LocalDateTime.of(2018, Month.JULY, 12, 13, 25), LocalDateTime.of(2018, Month.AUGUST, 1, 4, 56)), USER_ID_ONE);
        saveCategory(new Category(USER_ID_ONE, "Python", LocalDateTime.of(2018, Month.APRIL, 15, 7, 15), LocalDateTime.of(2018, Month.MAY, 1, 18, 45)), USER_ID_ONE);

        saveCategory(new Category(USER_ID_TWO, "JSP", LocalDateTime.of(2018, Month.JUNE, 29, 19, 7), LocalDateTime.of(2018, Month.AUGUST, 15, 14, 55)), USER_ID_TWO);
    }

    @Override
    public Category saveCategory(Category category, int userId) {
        Objects.requireNonNull(category);

        Integer categoryId = category.getId();

        Map<Integer, Category> categoryMap = userCategories.get(userId);
        if (categoryId == null) {
            categoryId = counter.incrementAndGet();
            category.setId(categoryId);
        } else {
            if (categoryMap == null || categoryMap.get(categoryId) == null) {
                return null;
            }
            else {
                categoryMap.put(categoryId, category);
                return category;
            }
        }

        if (categoryMap == null) {
            categoryMap = new ConcurrentHashMap<>();
            categoryMap.put(categoryId, category);
            userCategories.put(userId, categoryMap);
        }
        else {
            categoryMap.put(categoryId, category);
        }
        return category;
    }

    @Override
    public boolean deleteCategory(int categoryId, int userId) {
        Map<Integer, Category> categoryMap = userCategories.get(userId);
        return  categoryMap != null
                && categoryMap.remove(categoryId) != null
                && categoryNotes.remove(categoryId) != null;
    }

    @Override
    public List<Category> getUserCategories(int userId) {
        Map<Integer, Category> categoryMap = userCategories.get(userId);
        return categoryMap == null ? Collections.emptyList() : new ArrayList<Category>(categoryMap.values());
    }

    @Override
    public Note saveNote(Note note, int categoryId, int userId) {
        Objects.requireNonNull(note);

        Integer noteId = note.getId();

        Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
        if (noteId == null) {
            noteId = counter.incrementAndGet();
            note.setId(noteId);
        } else {
            if (noteMap == null || noteMap.get(noteId) == null) {
                return null;
            }
            else {
                noteMap.put(noteId, note);
                return note;
            }
        }

        if (noteMap == null) {
            noteMap = new ConcurrentHashMap<>();
            noteMap.put(noteId, note);
            categoryNotes.put(categoryId, noteMap);
        }
        else {
            noteMap.put(noteId, note);
        }
        return note;
    }

    @Override
    public boolean deleteNote(int noteId, int categoryId, int userId) {
        Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
        return  noteMap != null
                && noteMap.remove(noteId) != null;
    }

    @Override
    public boolean deleteCategoryNotes(int categoryId, int userId) {
        Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
        noteMap.clear();
        return true;
    }

    @Override
    public List<Note> getCategoryNotes(int categoryId, int userId) {
        Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
        return noteMap == null ? Collections.emptyList() : new ArrayList<Note>(noteMap.values());
    }

    @Override
    public List<Note> getUserNotes(int userId) {
        Map<Integer, Category> categoryMap = userCategories.get(userId);
        if (categoryMap == null) return null;

        List<Note> noteList = new ArrayList<>();
        for (Integer categoryId : categoryMap.keySet()) {
            Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
            if (noteMap != null)
                noteList.addAll(noteMap.values());
        }
        return noteList;
    }

    @Override
    public Note getNote(int noteId, int categoryId, int userId) {
        Map<Integer, Note> noteMap = categoryNotes.get(categoryId);
        return noteMap == null ? null : noteMap.get(noteId);
    }
}
