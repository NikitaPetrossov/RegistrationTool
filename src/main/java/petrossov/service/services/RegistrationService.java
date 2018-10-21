package petrossov.service.services;

import petrossov.service.forms.UserForm;
import petrossov.service.models.User;
import petrossov.service.transfer.UserDtoLogin;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    void registration(UserForm userForm);
    void registration(User user);

    void update(User user);
    void remove(Integer id);
    void remove(User user);
    Optional<User> findOne(UserDtoLogin userDtoLogin);
    User findOneById(Integer id);
    List<User> findAll();
}
