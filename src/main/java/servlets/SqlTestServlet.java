package servlets;

import model.User;
import service.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * created: 15-10-2020 - 19:36
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/sql")
public class SqlTestServlet extends HttpServlet {
    DataSource dataSource;
    UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        dataSource = (DataSource) servletContext.getAttribute("dataSource");
        usersService = (UsersService) servletContext.getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        writer.write("sql test servlet");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setName(name);
        user.setSurname(surname);

        System.out.println(user.toString());

        usersService.addUser(user);

        List<User> users = usersService.getAll();
        for (User usr : users) {
            writer.write(usr.toString() + "\n");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
