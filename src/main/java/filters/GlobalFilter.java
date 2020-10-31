package filters;

import service.CookieService;
import service.webapputils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class GlobalFilter implements Filter {

    private CookieService<Long> cookieService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        cookieService = (CookieService<Long>) servletContext.getAttribute("cookieService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.getAttribute("user_id") == null) {

            String sessionId = null;
            Cookie cookie = CookieUtils.getCookie(httpServletRequest.getCookies(),"session_id");
            if (cookie != null) {
                sessionId = cookie.getValue();
            }

            if (sessionId != null) {
                httpSession.setAttribute("user_id", cookieService.getUserId(sessionId));
            }
        }
        chain.doFilter(request, response);
    }
}
