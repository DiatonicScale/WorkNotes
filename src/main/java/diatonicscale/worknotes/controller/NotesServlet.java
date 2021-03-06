/**
 * User: DiatonicScale
 * Date: 27.08.2018
 */

package diatonicscale.worknotes.controller;

import diatonicscale.worknotes.model.Category;
import diatonicscale.worknotes.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class NotesServlet extends HttpServlet {
    private static Logger LOGGER = LoggerFactory.getLogger(NotesServlet.class);

    private NotesController notesController;
    private ConfigurableApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext("spring/spring.xml");
        notesController = context.getBean(NotesController.class);
    }

    @Override
    public void destroy() {
        context.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        String id = request.getParameter("id");
        if (action.equals("saveNote")) {
            int categoryId = Integer.valueOf(request.getParameter("categoryId"));
            Note note = new Note(id.isEmpty() ? null : Integer.valueOf(id),
                    categoryId,
                    request.getParameter("name"),
                    request.getParameter("value"));
            try {
                if (note.getId() == null)
                    notesController.addNote(note, categoryId);
                else
                    notesController.updateNote(note, categoryId);
            } catch (SQLException e) {
                catchSQLException(e, request, responce);
            }
            responce.sendRedirect("notes");
        } else if (action.equals("saveCategory")) {
            Category category = new Category(id.isEmpty() ? null : Integer.valueOf(id),
                    LoggedInUser.getId(),
                    request.getParameter("name"));
            try {
                if (category.getId() == null) {
                    notesController.addCategory(category);
                } else {
                    notesController.updateCategory(category);
                }
            } catch (SQLException e) {
                catchSQLException(e, request, responce);
            }
            responce.sendRedirect("notes");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                request.setAttribute("userCategories", notesController.getUserCategories());
                request.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(request, responce);
                return;
            }

            switch (action) {
                case "deleteCategory":
                    notesController.deleteCategory(getId(request));
                    responce.sendRedirect("notes");
                    break;
                case "addCategory":
                case "updateCategory":
                    Category category = (request.getParameter("id") == null
                            ? new Category(LoggedInUser.getId(), "", null, null)
                            : notesController.getCategory(getId(request)));
                    request.setAttribute("category", category);
                    request.getRequestDispatcher("/WEB-INF/jsp/editCategory.jsp").forward(request, responce);
                    break;
                case "categoryNotes":
                    List<Note> categoryNotes = notesController.getCategoryNotes(getId(request));
                    request.setAttribute("isEmpty", categoryNotes.isEmpty());
                    request.setAttribute("category", notesController.getCategory(getId(request)));
                    request.setAttribute("categoryNotes", categoryNotes);
                    request.getRequestDispatcher("/WEB-INF/jsp/notes.jsp").forward(request, responce);
                    break;
                case "allNotes":
                    List<Note> allNotes = notesController.getUserNotes();
                    request.setAttribute("isEmpty", allNotes.isEmpty());
                    request.setAttribute("userNotes", allNotes);
                    request.getRequestDispatcher("/WEB-INF/jsp/allNotes.jsp").forward(request, responce);
                    break;
                case "addNote":
                case "updateNote":
                    Note note = (request.getParameter("id") == null
                            ? new Note(Integer.valueOf(request.getParameter("categoryId")), "", null, null, "")
                            : notesController.getNote(getId(request)));
                    request.setAttribute("note", note);
                    request.getRequestDispatcher("/WEB-INF/jsp/editNote.jsp").forward(request, responce);
                    break;
                case "deleteNote":
                    int categoryId = Integer.valueOf(request.getParameter("categoryId"));
                    notesController.deleteNote(getId(request));
                    if (request.getParameter("allNotes") != null) {
                        responce.sendRedirect("notes?action=allNotes");
                        return;
                    }
                    responce.sendRedirect("notes?action=categoryNotes&id=" + categoryId);
                    break;
                case "deleteCategoryNotes":
                    notesController.deleteCategoryNotes(getId(request));
                    responce.sendRedirect("notes?action=categoryNotes&id=" + getId(request));
                    break;
            }
        } catch (SQLException e) {
            catchSQLException(e, request, responce);
        }
    }

    private int getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(id);
    }

    private void catchSQLException(Exception e, HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        LOGGER.error("Data Access error, reason: {}", e.getMessage());
        request.getRequestDispatcher("/WEB-INF/jsp/dbError.jsp").forward(request, responce);
    }
}
