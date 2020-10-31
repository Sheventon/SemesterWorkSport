package servlets;

import service.CookieService;
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
    private UsersService<Long> usersService;
    private CookieService<Long> cookieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService<Long>) servletContext.getAttribute("usersService");
        cookieService = (CookieService<Long>) servletContext.getAttribute("cookieService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sign up - doGet");
        request.getServletContext().getRequestDispatcher("/html/register.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String checkbox = request.getParameter("remember_me");

        if (!usersService.userIsExist(email)) {
            Long userId = usersService.signUp(name, surname, email, password);
            if (checkbox != null) {
                cookieService.rememberUser(response, userId);
            }
            request.getSession().setAttribute(USER_ID, userId);
            response.sendRedirect("/account");
        } else {
            AlertUtils.show(response.getWriter(),"User with this email already exist", "/login");
            response.sendRedirect("/login");
        }
    }
}
