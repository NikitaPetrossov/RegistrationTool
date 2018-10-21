package petrossov.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import petrossov.service.forms.UserForm;
import petrossov.service.models.Role;
import petrossov.service.models.State;
import petrossov.service.models.User;
import petrossov.service.repositories.UsersRepository;
import petrossov.service.transfer.UserDtoLogin;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registration(UserForm userForm) {
        try {

            String hashPassword = passwordEncoder.encode(userForm.getPassword());
            User user = User.builder()
                    .lastName(userForm.getLastName())
                    .name(userForm.getName())
                    .hashPassword(hashPassword)
                    .date(userForm.getDate())
                    .login(userForm.getLogin())
                    .role(Role.USER)
                    .state(State.ACTIVE)
                    .build();
            usersRepository.save(user);
            logger.info("Registration method save " + user);
        }  catch (Exception e) {
            logger.error("Error in registration method! ");
        }
    }

    @Override
    public void registration(User user) {
        try {

            String hashPassword = passwordEncoder.encode(user.getHashPassword());
            User newUser = User.builder()
                    .lastName(user.getLastName())
                    .name(user.getName())
                    .hashPassword(hashPassword)
                    .date(user.getDate())
                    .login(user.getLogin())
                    .role(Role.USER)
                    .state(State.ACTIVE)
                    .build();
            usersRepository.save(user);
            logger.info("Registration method save " + user);
        }  catch (Exception e) {
            logger.error("Error in registration method! ");
        }

    }

    @Override
    public void update(User user) {
        try {
            usersRepository.save(user);
            logger.info("Updating method update " + user);
        } catch (Exception e) {
            logger.error("Error in update method! ");
        }
    }


    @Override
    public void remove(Integer id) {

        try {
            usersRepository.delete(id);
            logger.info("Deleting method remove  " +id +  " user");
        } catch (Exception e) {
            logger.error("Error in remove method! ");
        }
    }

    @Override
    public void remove(User user) {
        usersRepository.delete(user);
    }


    @Override
    public Optional<User> findOne(UserDtoLogin userDtoLogin) {
        try {
            logger.info("Finding method get  " + userDtoLogin.getLogin());
            return usersRepository.findOneByLogin(userDtoLogin.getLogin());
        } catch (Exception e) {
            logger.error("Error in finding method! ");
            throw  new IllegalArgumentException("Error in finding method!");
        }
    }

    @Override
    public User findOneById(Integer id) {
        try {
            logger.info("Finding method get  " + id + "user");
            return  usersRepository.findOne(id);
        } catch (Exception e) {
            logger.error("Error in finding method! ");
            throw  new IllegalArgumentException("Error in finding method!");
        }
    }

    @Override
    public List<User> findAll() {

        return usersRepository.findAll();
    }
}
