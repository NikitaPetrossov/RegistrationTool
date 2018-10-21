package petrossov.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import petrossov.service.models.User;

@Data
@AllArgsConstructor
@Builder
public class UserDtoLogin {
    private String login;

    public static UserDtoLogin from(User user){
        return UserDtoLogin.builder()
                .login(user.getLogin())
                .build();
    }
}
