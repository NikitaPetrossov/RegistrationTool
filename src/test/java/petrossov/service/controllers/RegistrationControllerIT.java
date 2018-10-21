package petrossov.service.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import petrossov.service.app.Application;
import petrossov.service.forms.UserForm;
import petrossov.service.models.Role;
import petrossov.service.models.State;
import petrossov.service.models.User;
import petrossov.service.repositories.UsersRepository;
import petrossov.service.services.RegistrationService;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerIT {

    @Autowired
    private RegistrationService registrationService;

    private static Logger logger  = LoggerFactory.getLogger(CrudController.class);

    private static User user;
    @Before
    public void createUser(){
     user = User.builder()
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
    public void registration() {
        registrationService.registration(user);
        logger.info("Registration user is completed in test");
    }
    @After
    public void delete(){
        registrationService.remove(user.getId());
        logger.info("Test delete user");
    }
}