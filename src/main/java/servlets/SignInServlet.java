package servlets;

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

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private static final String USER_ID = "user_id";
    private AuthenticationService authenticationService;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        authenticationService = (AuthenticationService) servletContext.getAttribute("authenticationService");
        usersService = (UsersService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login - doGet");
        request.getServletContext().getRequestDispatcher("/html/login.html").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String checkbox = request.getParameter("remember_me");

        if (authenticationService.authenticateUser(email, usersService.generateSecurePassword(password))) {
            if (checkbox != null) {
                authenticationService.rememberUser(request, response, email);
            }
            request.getSession().setAttribute(USER_ID, usersService.getByEmail(email).getId());
            response.sendRedirect("/account");
        } else {
            AlertUtils.show(response.getWriter(), "Incorrect email or password.", "/login");
        }

    }
}
