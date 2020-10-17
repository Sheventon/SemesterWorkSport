package filters;

import service.AuthenticationService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class GlobalFilter implements Filter {

    private AuthenticationService authenticationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        authenticationService = (AuthenticationService) servletContext.getAttribute("authenticationService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.getAttribute("user_id") == null) {

            Cookie[] cookies = httpServletRequest.getCookies();
            String sessionId = null;

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("session_id")) {
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }
            if (sessionId != null) {
                Long userId = authenticationService.getUserBySession(
                        httpServletRequest,
                        httpServletResponse,
                        sessionId);
                httpSession.setAttribute("user_id", userId);
            }
        }
        chain.doFilter(request, response);
    }
}
