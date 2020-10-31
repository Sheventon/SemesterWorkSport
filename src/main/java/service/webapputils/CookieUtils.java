package service.webapputils;

import javax.servlet.http.Cookie;
import java.util.Optional;

/**
 * created: 30-10-2020 - 20:20
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class CookieUtils {

    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
