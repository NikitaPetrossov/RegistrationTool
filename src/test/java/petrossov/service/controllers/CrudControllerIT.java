package petrossov.service.controllers;

import com.sun.deploy.net.HttpResponse;
import org.apache.tomcat.jdbc.pool.DataSource;
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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrudControllerIT {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private DataSource dataSource;

    private static Logger logger  = LoggerFactory.getLogger(CrudController.class);

    private static User user =  User.builder()
            .id(1000)
                .lastName("LastName")
                .name("Name")
                .hashPassword("password")
                .date("date")
                .login("login")
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

    @Test
    public void isListOfUsersNoEmpty() {
        List<User> users = registrationService.findAll();
        logger.info("Test show All users: "+ Arrays.toString(users.toArray()));
        assertNotNull(users);
    }
    @Test
    public void dBConnection() throws SQLException {
        logger.info("Connection: " + dataSource.getConnection());
        assert(dataSource.getConnection()!= null);
    }


    @Test
    public void isSaveHashPasswordWhileUpdateUser() {
        String hashPassword =  passwordEncoder.encode(String.valueOf(Math.random()*100));
        user.setHashPassword(hashPassword);
        registrationService.update(user);
        logger.info("Test update user " + user.toString());
        User userFromDB =usersRepository.findOneByLogin("login").get();
        assertEquals(userFromDB.getHashPassword(), hashPassword);
       registrationService.remove(userFromDB);
    }

}