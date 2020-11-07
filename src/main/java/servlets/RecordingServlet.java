package servlets;

import service.RecordsService;
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
 * created: 29-10-2020 - 14:11
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/recording")
public class RecordingServlet extends HttpServlet {

    private RecordsService<Long> recordsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        recordsService = (RecordsService<Long>) servletContext.getAttribute("recordsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/html/recording.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String section = request.getParameter("section");
        Long userId = (Long) request.getSession().getAttribute("user_id");

        System.out.println(section);

        if (recordsService.recordToSection(userId, section)) {
            response.sendRedirect("/home");
        } else {
            AlertUtils.show(response.getWriter(), "Sorry, you already have record on this kind of sport", "/recording");
        }
    }
}
