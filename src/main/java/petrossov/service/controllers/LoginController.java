package petrossov.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    private static Logger logger  = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication,
                               ModelMap model,
                               HttpServletRequest request) {
        if (authentication != null){
            logger.info("Successful login.");
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
            logger.error("Login Error!");
        }
        return "login";
    }
}
