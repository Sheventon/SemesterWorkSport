package servlets;

import service.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 06-11-2020 - 23:56
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/check_email")
public class CheckEmailServlet extends HttpServlet {

    private UsersService<Long> usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService<Long>) servletContext.getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        if (usersService.getByEmail(email).isPresent()) {
            response.getWriter().write("incorrect");
        } else {
            response.getWriter().write("correct");
        }
    }
}
