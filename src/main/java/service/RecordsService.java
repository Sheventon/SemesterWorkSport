package service;

import java.util.List;

/**
 * created: 30-10-2020 - 21:53
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public interface RecordsService<ID> {
    boolean recordToSection(ID userId, String sectionName);
    List<String> getAllSections(ID userId);
}
