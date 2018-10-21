package petrossov.service.forms;

import lombok.*;
import petrossov.service.models.User;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class UserForm  {
    private Integer id;
    private String lastName;
    private String name;
    private String password;
    private String date;
    private String login;
}
