package servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home"})
public class HomeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookie = httpRequest.getCookies();

        boolean active = false;
        for (Cookie c : cookie) {
            if (c.getName().equals("status") && c.getValue().equals("active")) {
                active = true;
                break;
            }
        }
        if (active) {
            ((HttpServletResponse) response).sendRedirect("/home_authorized");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
