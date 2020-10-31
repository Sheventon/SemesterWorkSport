package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 30-10-2020 - 22:02
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Section {
    private Integer id;
    private Integer trainer_id;
    private Integer sport_id;
}
