package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 07-11-2020 - 0:21
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/judo")
public class JudoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/html/judo.html").forward(request, response);
    }
}
