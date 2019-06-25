package webstore.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class User {

    @NotNull
    @Size(min = 6, max = 30)
    private String login;

    @NotNull
    @Size(min = 6, max = 30)
    private String password;

}
