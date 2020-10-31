package service;

import model.Record;
import model.Section;
import repositories.RecordsRepository;
import repositories.SectionsRepository;

import java.util.List;
import java.util.Optional;

/**
 * created: 30-10-2020 - 21:56
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
public class RecordsServiceImpl implements RecordsService<Long> {

    private final RecordsRepository recordsRepository;
    private final SectionsRepository sectionsRepository;

    public RecordsServiceImpl(RecordsRepository recordsRepository, SectionsRepository sectionsRepository) {
        this.recordsRepository = recordsRepository;
        this.sectionsRepository = sectionsRepository;
    }

    @Override
    public boolean recordToSection(Long userId, String sectionName) {
        Optional<Section> section = sectionsRepository.findByName(sectionName);
        if(!recordsRepository.check(userId,sectionName)) {
            if (section.isPresent()) {
                Record record = new Record(null, userId, section.get().getId());
                System.out.println(record);
                recordsRepository.save(record);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getAllSections(Long userId) {
        return recordsRepository.findAllSectionsByUserId(userId);
    }
}
