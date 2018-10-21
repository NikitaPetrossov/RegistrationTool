package petrossov.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import petrossov.service.security.details.UserDetailsImpl;
import petrossov.service.transfer.UserDto;

import static petrossov.service.transfer.UserDto.from;

@Controller
public class ProfileController {
    private static Logger logger  = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication){
        if (authentication == null){
            logger.info("Authentication failed!");
            return "redirect:/login";
        }
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        UserDto userDto = from(details.getUser());
        model.addAttribute("user",userDto);
        logger.info("User " + userDto.getLastName() + " come in.");
        return "profile";
    }
}
