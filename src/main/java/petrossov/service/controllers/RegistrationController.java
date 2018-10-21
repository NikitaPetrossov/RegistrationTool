package petrossov.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import petrossov.service.forms.UserForm;
import petrossov.service.models.User;
import petrossov.service.repositories.UsersRepository;
import petrossov.service.services.RegistrationService;

@Controller
public class RegistrationController {
    private static Logger logger  = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String setUsersPage(){
        logger.info("Open registration page.");

        return "registration";

    }
    @PostMapping("/registration")
    public String registration( UserForm userForm) {
        try {
            registrationService.registration(userForm);
            logger.info("Registration of " + userForm + "successful!");
            return "redirect:/crud/";
        } catch (Exception e) {
            logger.error("Registration error! ");
            return "Error";
        }
    }
}
