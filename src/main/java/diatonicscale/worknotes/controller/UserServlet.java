/**
 * User: DiatonicScale
 * Date: 13.08.2018
 */

package diatonicscale.worknotes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = LoggerFactory.getLogger(UserServlet.class);
        logger.debug("It's debug 1");
        logger.info("It's info 1");
        logger.error("It's error 1");

    // TODO: do with redirect to /categories after sign in
        req.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(req, resp);

        logger.debug("It's debug 2");
        logger.info("It's info 2");
        logger.error("It's error 2");
}
}
