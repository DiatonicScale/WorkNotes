/**
 * User: DiatonicScale
 * Date: 13.08.2018
 */

package diatonicscale.worknotes.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class usersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: do with redirect to /categories after sign in
        req.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(req, resp);
    }
}
