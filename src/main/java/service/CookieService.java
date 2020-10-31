package service;

import model.UserCookie;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * created: 15-10-2020 - 12:17
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface CookieService<ID> {
    void rememberUser(HttpServletResponse response, ID userId);
    ID getUserId(String sessionId);
}
