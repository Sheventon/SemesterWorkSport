package servlets;

import model.User;
import service.AuthenticationService;
import service.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * created: 16-10-2020 - 17:40
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    UsersService usersService;
    AuthenticationService authenticationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("userService");
        authenticationService = (AuthenticationService) servletContext.getAttribute("authenticationService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("profile - doGet");

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");

        User user = usersService.getUser(userId);

        request.setAttribute("name", user.getName());
        request.setAttribute("surname", user.getSurname());
        request.setAttribute("patronymic", user.getPatronymic() == null ? "" : user.getPatronymic());
        request.setAttribute("email", user.getEmail());

        request.getServletContext().getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }
}
