package service;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created: 15-10-2020 - 11:48
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface AuthenticationService {
    //Аутентифицируйте пользователя (проверка имени пользователя и пароля).
    boolean authenticateUser(String email, String password);

    //Сохраните запомненного пользователя.
    void rememberUser(HttpServletRequest request, HttpServletResponse response, String email);

    //Получите запомненного пользователя.
    User getRememberedUser(String sessionId);

    //Удалите запомненного пользователя.
    boolean deleteRememberedUser(String id);

    Long getUserBySession(HttpServletRequest request, HttpServletResponse response, String sessionId);
}
