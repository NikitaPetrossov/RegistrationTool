package petrossov.service.controllers;

import com.sun.deploy.net.HttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import petrossov.service.app.Application;
import petrossov.service.models.FakeStorage;
import petrossov.service.models.Role;
import petrossov.service.models.State;
import petrossov.service.models.User;
import petrossov.service.repositories.UsersRepository;
import petrossov.service.services.RegistrationService;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudControllerIT {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Logger logger  = LoggerFactory.getLogger(CrudController.class);

    private static User user;

    @Before
    public void createUser(){
        user = User.builder()
                .id(1000)
                .lastName("LastName")
                .name("Name")
                .hashPassword("password")
                .date("date")
                .login("login")
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();
        logger.info("Test create user");
    }

    @Test
    public void getUsers() {
        List<User> users = registrationService.findAll();
        logger.info("Test show All users: "+ Arrays.toString(users.toArray()));
    }

    @Test
    public void deleteUser() {
        registrationService.remove(user);
        logger.info("Test delete userForDelete");

    }

    @Test
    public void findOneUser() {
         registrationService.findOneById(user.getId());
        logger.info("Test show one user " + user.toString());

    }

    @Test
    public void updateUser() {

        registrationService.findOneById(user.getId());
        user.setLogin(String.valueOf(Math.random()*100));
        String hashPassword =  passwordEncoder.encode(String.valueOf(Math.random()*100));
        user.setHashPassword(hashPassword);
        logger.info("Test update user " + user.toString());
    }
    @After
    public void delete(){
        registrationService.remove(user);
        logger.info("Test delete user");
    }
}