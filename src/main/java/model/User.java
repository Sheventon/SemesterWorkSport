package model;

import lombok.*;

/**
 * created: 14-10-2020 - 21:14
 * project: SemesterWorkSport
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}


