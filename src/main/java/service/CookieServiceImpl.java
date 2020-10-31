package service;

import model.UserCookie;
import repositories.UserCookiesRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * created: 30-10-2020 - 18:52
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class CookieServiceImpl implements CookieService<Long> {

    private static final String SESSION_ID = "session_id";

    private final UserCookiesRepository cookiesRepository;

    public CookieServiceImpl(UserCookiesRepository cookiesRepository) {
        this.cookiesRepository = cookiesRepository;
    }

    @Override
    public void rememberUser(HttpServletResponse response, Long userId) {

        Optional<UserCookie> userCookie = cookiesRepository.findById(userId);

        Cookie cookie;

        if (userCookie.isPresent()) {
            cookie = new Cookie(SESSION_ID, userCookie.get().getSessionId());
        } else {
            String sessionId;

            do {
                sessionId = UUID.randomUUID().toString();
            } while (cookiesRepository.findBySessionId(sessionId).isPresent());

            UserCookie newUserCookie = new UserCookie(userId, sessionId);
            cookiesRepository.save(newUserCookie);

            cookie = new Cookie(SESSION_ID, sessionId);
        }

        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 90);

        response.addCookie(cookie);
    }

    @Override
    public Long getUserId(String sessionId) {
        Optional<UserCookie> userCookie = cookiesRepository.findBySessionId(sessionId);
        return userCookie.map(UserCookie::getUserId).orElse(null);
    }
}
