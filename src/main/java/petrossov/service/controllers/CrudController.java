package petrossov.service.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import petrossov.service.models.User;
import petrossov.service.repositories.UsersRepository;
import petrossov.service.security.details.UserDetailsImpl;
import petrossov.service.services.RegistrationService;
import petrossov.service.transfer.UserDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

import static petrossov.service.transfer.UserDto.from;

@Controller
public class CrudController {

    private static Logger logger  = LoggerFactory.getLogger(CrudController.class);


    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CrudController() throws IOException {
    }


    @GetMapping("/crud")
    public ModelAndView setUsersPage( Authentication authentication){
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto user = from(details.getUser());

        logger.info("User " +user + "Open CRUD page");
        return new ModelAndView("Crud");


    }

    @PostMapping(path = "/crud", headers = {"Content-type=application/json"})
    @ResponseBody
    public void getUsers( HttpServletResponse httpServletResponse) throws IOException {
        List<User> users = usersRepository.findAll();
        logger.info("Show users from DataBase");
        Gson gson = new Gson();
        String result = String.valueOf(gson.toJson(users));
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write( result);

    }


    @RequestMapping (value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteUser(Integer deletingId){
        try {
            registrationService.remove(deletingId);
            logger.info("delete user with id = " + deletingId);
          return new ModelAndView("Crud");
        }catch (Exception e){
            logger.info("user with id = " + deletingId + " not found!");
            return new ModelAndView("Crud");
        }
    }

    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public User findOneUser( Integer id)  {
        User user = registrationService.findOneById(id);
        try {
            if (user != null) {
                logger.info("show user with id = " + id);
                return user;
            }else {
                throw new IllegalArgumentException("");
            }
        }catch (IllegalArgumentException e){
            logger.error("user with id = " + id  + " not found!");
            throw  new IllegalArgumentException("user with id = " + id  + " not found!");
        }
    }
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(Integer id, String login, String password){
        try {
            User user = registrationService.findOneById(id);
            user.setLogin(login);
            String hashPassword = passwordEncoder.encode(password);
            user.setHashPassword(hashPassword);
            registrationService.update(user);
            logger.info("update user with id = " + id + " : "
                    + "new login is " + login);
            return "OK";
        } catch (Exception e) {
            logger.error("Updating  error! ");
            return "Error";
        }

    }
}
