package petrossov.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import petrossov.service.models.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private String lastName;
    private String name;

    public static UserDto from(User user){
        return UserDto.builder()
                .lastName(user.getLastName())
                .name(user.getName())
                .build();
    }
}
