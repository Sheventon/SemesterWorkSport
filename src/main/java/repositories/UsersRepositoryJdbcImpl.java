package repositories;

import model.User;

import java.util.List;
import java.util.Optional;

/**
 * created: 14-10-2020 - 22:13
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class UsersRepositoryJdbcImpl implements UsersRepository {

    // language=SQL
    private static final String SELECT_USER_BY_EMAIL = "select * from user where email = ?";
    // language=SQL
    private static final String SELECT_ALL_USERS = "select * from user";
    // language=SQL
    private static final String INSERT_USER = "insert into user(name, surname, patronymic, email, password) values (?, ?, ?, ?, ?)";
    // language=SQL
    private static final String SELECT_USER_BY_ID = "select * from user where id = ?";
    // language=SQL
    private static final String UPDATE_DATA = "update user set name = ?, surname = ?, patronymic = ?, email = ?, password = ? where  id = ?";

    private final SimpleJdbcTemplate template;

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .patronymic(row.getString("patronymic"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();

    public UsersRepositoryJdbcImpl(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.template = simpleJdbcTemplate;
    }

    @Override
    public void save(User entity) {
        template.updateQuery(INSERT_USER,
                entity.getName(),
                entity.getSurname(),
                entity.getPatronymic(),
                entity.getEmail(),
                entity.getPassword());
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void update(User entity) {
        template.updateQuery(UPDATE_DATA,
                entity.getName(),
                entity.getSurname(),
                entity.getPatronymic(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getId());
    }

    @Override
    public Optional<User> findById(Long id) {
        return template.selectQuery(SELECT_USER_BY_ID, userRowMapper, id).stream().findFirst();
    }

    @Override
    public List<User> findAll() {
        return template.selectQuery(SELECT_ALL_USERS, userRowMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return template.selectQuery(SELECT_USER_BY_EMAIL, userRowMapper, email).stream().findFirst();
    }

    @Override
    public Long saveWithReturnId(User user) {
        return template.updateQuery(INSERT_USER,
                user.getName(),
                user.getSurname(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPassword());
    }
}
