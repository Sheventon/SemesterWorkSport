package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 30-10-2020 - 21:48
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private Long id;
    private Long userId;
    private Integer section_id;
}
