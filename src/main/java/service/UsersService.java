package service;

import model.User;

import java.util.Optional;

/**
 * created: 14-10-2020 - 23:08
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersService<ID> {

    ID signUp(String firstname, String lastname, String email, String password);

    ID signIn(String email, String password);

    boolean userIsExist(String email);

    String generateSecurePassword(String password);

    Optional<User> getById(ID id);

    Optional<User> getByEmail(String email);

    boolean updateUserData(ID id, String patronymic, String password);
}
