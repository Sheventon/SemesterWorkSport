package service;

import model.User;
import repositories.UsersRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

/**
 * created: 14-10-2020 - 23:10
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class UsersServiceImpl implements UsersService<Long> {

    private final UsersRepository usersRepository;

    private static final String SALT = "1234567890-=+()[]{};',./<>?!:";

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Long signUp(String name, String lastName, String email, String password) {
        User user = new User(name, lastName, email, generateSecurePassword(password));
        return usersRepository.saveWithReturnId(user);
    }

    @Override
    public Long signIn(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(generateSecurePassword(password))) {
            return user.get().getId();
        }
        return null;
    }

    @Override
    public boolean userIsExist(String email) {
        return usersRepository.findByEmail(email).isPresent();
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

    @Override
    public Optional<User> getById(Long id) {
        return usersRepository.findById(id);
    }
}

