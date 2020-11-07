package servlets;

import model.User;
import service.RecordsService;
import service.UsersService;
import service.webapputils.AlertUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * created: 16-10-2020 - 17:40
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private UsersService<Long> usersService;
    private RecordsService<Long> recordsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService<Long>) servletContext.getAttribute("usersService");
        recordsService = (RecordsService<Long>) servletContext.getAttribute("recordsService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");

        Optional<User> user = usersService.getById(userId);
        List<String> sections = recordsService.getAllSections(userId);

        user.ifPresent(usr -> {
            request.setAttribute("name", usr.getName());
            request.setAttribute("surname", usr.getSurname());
            request.setAttribute("patronymic", usr.getPatronymic() == null ? "" : usr.getPatronymic());
            request.setAttribute("email", usr.getEmail());
            request.setAttribute("sections", sections);
        });

        request.getServletContext().getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patronymic = request.getParameter("patronymic").trim();
        String password = request.getParameter("password").trim();
        Long id = (Long) request.getSession().getAttribute("user_id");

        if (!usersService.updateUserData(id, patronymic, password)) {
            AlertUtils.show(response.getWriter(), "Please enter correct data", "/account");
        } else {
            response.sendRedirect("/account");
        }
    }
}
