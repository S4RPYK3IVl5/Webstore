package webstore.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Table(name = "usr")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 6, max = 30)
    private String login;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

}


