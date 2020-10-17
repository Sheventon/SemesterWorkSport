package service;

import model.User;

import java.util.List;

/**
 * created: 14-10-2020 - 23:08
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersService {
    //Найдите пользователя по email
    User getByEmail(String email);

    List<User> getAll();

    void addUser(User user);

    User getUser(Long id);

    boolean deleteUser(String id);

    boolean userIsExist(String email);

    String generateSecurePassword(String password);

}
