package repositories;

import java.util.List;

/**
 * created: 14-10-2020 - 22:07
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudRepository<T> {
    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Long id);

    List<T> findAll();
}
