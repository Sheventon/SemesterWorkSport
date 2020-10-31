package repositories;

import model.UserCookie;

import java.util.Optional;

/**
 * created: 15-10-2020 - 18:57
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UserCookiesRepository extends CrudRepository<UserCookie, Long> {

    Optional<UserCookie> findBySessionId(String sessionId);
    Long saveWithReturnId(UserCookie userCookie);

}
