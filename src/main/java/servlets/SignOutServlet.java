package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * created: 16-10-2020 - 22:02
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/out")
public class SignOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Cookie cookie = new Cookie("session_id", "deleted");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        session.removeAttribute("user_id");
        session.invalidate();

        response.sendRedirect("/home");

    }
}
