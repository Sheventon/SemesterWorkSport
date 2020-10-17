package servlets;

import model.User;
import service.AuthenticationService;
import service.UsersService;
import service.webapputils.AlertUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 16-10-2020 - 17:43
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final String USER_ID = "user_id";
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
        System.out.println("sign up - doGet");
        request.getServletContext().getRequestDispatcher("/html/register.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String checkbox = request.getParameter("remember_me");

        User user = new User(name, surname, email, usersService.generateSecurePassword(password));

        if (!usersService.userIsExist(email)) {
            usersService.addUser(user);
            if (checkbox != null) {
                authenticationService.rememberUser(request, response, email);
            }
            request.getSession().setAttribute(USER_ID, usersService.getByEmail(email).getId());
            response.sendRedirect("/account");
        } else {
            AlertUtils.show(response.getWriter(), "User with this email already exists.", "/login");

        }
    }
}
