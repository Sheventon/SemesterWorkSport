package repositories;

import model.Section;

import java.util.Optional;

/**
 * created: 30-10-2020 - 22:02
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface SectionsRepository extends CrudRepository<Section, Integer> {
    Optional<Section> findByName(String name);
}
