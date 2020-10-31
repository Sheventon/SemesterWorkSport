package repositories;

import model.Section;

import java.util.List;
import java.util.Optional;

/**
 * created: 30-10-2020 - 22:04
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class SectionsRepositoryJdbcImpl implements SectionsRepository {

    // language=SQL
    private static final String SQL_SELECT_BY_NAME =
            "select s.id as id, s.sport_id as sport_id, s.trainer_id as trainer_id from section as s " +
                    "join sport s2 on s2.id = s.sport_id where s2.name = ?";

    private final RowMapper<Section> sectionRowMapper = row -> Section.builder()
            .id(row.getInt("id"))
            .sport_id(row.getInt("sport_id"))
            .trainer_id(row.getInt("trainer_id"))
            .build();

    private final SimpleJdbcTemplate simpleJdbcTemplate;

    public SectionsRepositoryJdbcImpl(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    @Override
    public void save(Section entity) {

    }

    @Override
    public void delete(Section entity) {

    }

    @Override
    public void update(Section entity) {

    }

    @Override
    public List<Section> findAll() {
        return null;
    }

    @Override
    public Optional<Section> findById(Integer aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Section> findByName(String name) {
        return simpleJdbcTemplate.selectQuery(SQL_SELECT_BY_NAME, sectionRowMapper, name).stream().findFirst();
    }
}
