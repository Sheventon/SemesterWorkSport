package repositories;

import java.util.List;
import java.util.Optional;

/**
 * created: 14-10-2020 - 22:07
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudRepository<T, ID> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();

    Optional<T> findById(ID id);
}
