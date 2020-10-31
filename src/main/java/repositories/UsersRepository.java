package repositories;

import model.User;

import java.util.Optional;

/**
 * created: 14-10-2020 - 22:11
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Long saveWithReturnId(User user);

}
