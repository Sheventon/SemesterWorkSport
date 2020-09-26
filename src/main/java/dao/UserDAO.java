package dao;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<User> store = new ArrayList<>();

    public boolean add(final User user) {
        for (User usr : store) {
            if (user.getEmail().equals(usr.getEmail())) {
                return false;
            }
        }
        return store.add(user);
    }

    public boolean userIsExist(final String fio, final String password) {
        for (User usr : store) {
            if (usr.getFIO().equals(fio) && usr.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
