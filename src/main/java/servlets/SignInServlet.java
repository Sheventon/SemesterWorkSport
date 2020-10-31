package servlets;

import service.AuthenticationService;
import service.CookieService;
import service.UsersService;
import service.webapputils.AlertUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private static final String USER_ID = "user_id";
    private CookieService<Long> cookieService;
    private UsersService<Long> usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService<Long>) servletContext.getAttribute("usersService");
        cookieService = (CookieService<Long>) servletContext.getAttribute("cookieService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("login - doGet");
        request.getServletContext().getRequestDispatcher("/html/login.html").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String checkbox = request.getParameter("remember_me");

        Long userId = usersService.signIn(email, password);

        if (userId != null) {
            if (checkbox != null) {
                cookieService.rememberUser(response, userId);
            }
            request.getSession().setAttribute(USER_ID, userId);
            response.sendRedirect("/account");
        } else {
            AlertUtils.show(response.getWriter(), "Incorrect email or password.", "/login");
        }

    }
}
