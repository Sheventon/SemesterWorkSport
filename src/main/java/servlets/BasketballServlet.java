package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 29-10-2020 - 14:04
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/basketball")
public class BasketballServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/html/basketball.html").forward(request, response);
    }
}
