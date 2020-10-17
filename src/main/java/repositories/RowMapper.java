package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created: 14-10-2020 - 22:16
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
