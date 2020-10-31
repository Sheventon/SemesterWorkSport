package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created: 30-10-2020 - 22:10
 * project: SemestrWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {
    private Integer id;
    private Long user_id;
    private String phoneNumber;
}
