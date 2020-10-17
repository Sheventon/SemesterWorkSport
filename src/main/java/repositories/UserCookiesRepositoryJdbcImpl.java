package repositories;

import model.UserCookie;

import javax.sql.DataSource;
import java.util.List;

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

    DataSource dataSource;

    SimpleJdbcTemplate template;

    public UserCookiesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.template = new SimpleJdbcTemplate(dataSource);
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
    public UserCookie findById(Long userId) {
        List<UserCookie> resultList = template.selectQuery(SELECT_USER_COOKIE_BY_USER_ID, userCookieRowMapper, userId);
        return resultList.isEmpty() ? null : resultList.get(0);

    }

    @Override
    public List<UserCookie> findAll() {
        return null;
    }

    @Override
    public UserCookie findBySessionId(String sessionId) {
        List<UserCookie> resultList = template.selectQuery(SELECT_USER_COOKIE_BY_SESSION_ID, userCookieRowMapper, sessionId);
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
