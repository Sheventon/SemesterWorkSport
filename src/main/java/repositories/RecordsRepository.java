package repositories;

import model.Record;

import java.util.List;

/**
 * created: 30-10-2020 - 21:47
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface RecordsRepository extends CrudRepository<Record, Long>{
    List<String> findAllSectionsByUserId(Long userID);

    boolean check(Long userId, String sectionName);
}
