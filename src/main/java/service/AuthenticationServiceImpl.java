package service;

import lombok.AllArgsConstructor;
import model.User;
import model.UserCookie;
import repositories.UserCookiesRepository;
import repositories.UsersRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * created: 15-10-2020 - 11:49
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */

@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    UsersRepository usersRepository;
    UserCookiesRepository userCookiesRepository;

    @Override
    public boolean authenticateUser(String email, String password) {
        User user = usersRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public Long getUserBySession(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        return userCookiesRepository.findBySessionId(sessionId).getUserId();
    }

    @Override
    public void rememberUser(HttpServletRequest request, HttpServletResponse response, String email) {
        Long userId = usersRepository.findByEmail(email).getId();
        String sessionId;

        UserCookie userCookie = userCookiesRepository.findById(userId);
        if (userCookie != null) {
            sessionId = userCookie.getSessionId();
        } else {

            do {
                sessionId = UUID.randomUUID().toString();
            } while (userCookiesRepository.findBySessionId(sessionId) != null);

            UserCookie newUserCookie = UserCookie.builder()
                    .userId(userId)
                    .sessionId(sessionId)
                    .build();

            userCookiesRepository.save(newUserCookie);
        }
        Cookie cookie = new Cookie("session_id", sessionId);

        cookie.setPath("/");
        cookie.setDomain(request.getServerName());
        cookie.setMaxAge(60 * 60 * 24 * 90);

        response.addCookie(cookie);
    }

    @Override
    public User getRememberedUser(String sessionId) {
        return null;
    }

    @Override
    public boolean deleteRememberedUser(String id) {
        return false;
    }

}
