package repositories;

import model.User;

import javax.sql.DataSource;
import java.util.List;

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

    private DataSource dataSource;

    private final SimpleJdbcTemplate template;

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .surname(row.getString("surname"))
            .patronymic(row.getString("patronymic"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .build();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
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

    }

    @Override
    public User findById(Long id) {
        List<User> userList = template.selectQuery(SELECT_USER_BY_ID, userRowMapper, id);
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    public List<User> findAll() {
        return template.selectQuery(SELECT_ALL_USERS, userRowMapper);
    }

    @Override
    public User findByEmail(String email) {
        List<User> userList = template.selectQuery(SELECT_USER_BY_EMAIL, userRowMapper, email);
        return userList.isEmpty() ? null : userList.get(0);
    }
}
