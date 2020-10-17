package repositories;

import model.User;

/**
 * created: 14-10-2020 - 22:11
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String email);
}
