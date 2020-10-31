package repositories;

import model.Record;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * created: 30-10-2020 - 21:49
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class RecordsRepositoryJdbcImpl implements RecordsRepository {

    // language=SQL
    private static final String SQL_INSERT_RECORD = "insert into record(user_id, section_id) values (?, ?)";

    // language=SQL
    private static final String SQL_SELECT_ALL_SECTIONS = "select sp.name as name from record r " +
            "join section s on s.id = r.section_id " +
            "join sport sp on sp.id = s.sport_id where r.user_id = ?";

    // language=SQL
    private static final String SQL_SELECT_BY_USER_ID_AND_SECTION_NAME = "select r.id from record r " +
            "join section s on s.id = r.section_id " +
            "join sport sp on sp.id = s.sport_id where r.user_id = ? and sp.name = ?";


    private final RowMapper<Record> recordRowMapper = row -> Record.builder()
            .id(row.getLong("id"))
            .userId(row.getLong("user_id"))
            .section_id(row.getInt("section_id"))
            .build();

    private final SimpleJdbcTemplate simpleJdbcTemplate;
    private final DataSource dataSource;

    public RecordsRepositoryJdbcImpl(SimpleJdbcTemplate simpleJdbcTemplate, DataSource dataSource) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
        this.dataSource = dataSource;
    }


    @Override
    public void save(Record entity) {
        entity.setId(simpleJdbcTemplate.updateQuery(SQL_INSERT_RECORD,
                entity.getUserId(),
                entity.getSection_id()));
    }

    @Override
    public void delete(Record entity) {

    }

    @Override
    public void update(Record entity) {

    }

    @Override
    public List<Record> findAll() {
        return null;
    }

    @Override
    public Optional<Record> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<String> findAllSectionsByUserId(Long userId) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_SELECT_ALL_SECTIONS)) {
            preparedStatement.setLong(1, userId);
            List<String> resultList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(resultSet.getString("name"));
            }
            return resultList;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean check(Long userId, String sectionName) {
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(SQL_SELECT_BY_USER_ID_AND_SECTION_NAME)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, sectionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
