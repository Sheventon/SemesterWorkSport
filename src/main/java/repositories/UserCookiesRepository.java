package repositories;

import model.UserCookie;

/**
 * created: 15-10-2020 - 18:57
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UserCookiesRepository extends CrudRepository<UserCookie> {
    UserCookie findBySessionId(String sessionId);
}
