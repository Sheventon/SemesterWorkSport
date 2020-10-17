package model;

import lombok.*;

/**
 * created: 15-10-2020 - 18:40
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserCookie {
    private Long userId;
    private String sessionId;
}
