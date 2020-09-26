package servlets.servlet;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get-login");
        req.getServletContext().getRequestDispatcher("/html/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post-login");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");

        final String fio = req.getParameter("fio");
        final String password = req.getParameter("password");
        final String check = req.getParameter("remember");
        // on - null

        if(dao.get().userIsExist(fio,password)) {
            Cookie cookie = new Cookie("status", "active");
            if(check != null) {
                cookie.setMaxAge(30);
            }
            resp.addCookie(cookie);
            resp.sendRedirect("/home");
        }
    }
}
