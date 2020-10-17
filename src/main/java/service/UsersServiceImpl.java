package service;

import model.User;
import repositories.UsersRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * created: 14-10-2020 - 23:10
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    private static final String SALT = "1234567890-=+()[]{};',./<>?!:";

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User getByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public boolean userIsExist(String email) {
        return getByEmail(email) != null;
    }

    @Override
    public String generateSecurePassword(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(SALT.getBytes());
            byte[] newValue = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(newValue);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}

