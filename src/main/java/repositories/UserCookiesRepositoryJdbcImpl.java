package repositories;

import model.UserCookie;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * created: 15-10-2020 - 18:57
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class UserCookiesRepositoryJdbcImpl implements UserCookiesRepository {

    // language=SQL
    private static final String SELECT_USER_COOKIE_BY_SESSION_ID = "select * from cookie where session_id = ?";
    // language=SQL
    private static final String INSERT_USER_COOKIE = "insert into cookie(user_id, session_id) values(?, ?)";
    // language=SQL
    private static final String SELECT_USER_COOKIE_BY_USER_ID = "select * from cookie where user_id = ?";

    private final RowMapper<UserCookie> userCookieRowMapper = row -> UserCookie.builder()
            .userId(row.getLong("user_id"))
            .sessionId(row.getString("session_id"))
            .build();

    private final SimpleJdbcTemplate template;

    public UserCookiesRepositoryJdbcImpl(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.template = simpleJdbcTemplate;
    }

    @Override
    public void save(UserCookie entity) {
        template.updateQuery(INSERT_USER_COOKIE,
                entity.getUserId(),
                entity.getSessionId());
    }

    @Override
    public void delete(UserCookie entity) {

    }

    @Override
    public void update(UserCookie entity) {

    }

    @Override
    public Optional<UserCookie> findById(Long userId) {
        return template.selectQuery(SELECT_USER_COOKIE_BY_USER_ID, userCookieRowMapper, userId).stream().findFirst();
    }

    @Override
    public List<UserCookie> findAll() {
        return null;
    }

    @Override
    public Optional<UserCookie> findBySessionId(String sessionId) {
        return template.selectQuery(SELECT_USER_COOKIE_BY_SESSION_ID, userCookieRowMapper, sessionId).stream().findFirst();
    }

    @Override
    public Long saveWithReturnId(UserCookie userCookie) {
        return template.updateQuery(INSERT_USER_COOKIE,
                userCookie.getUserId(),
                userCookie.getSessionId());
    }

}
