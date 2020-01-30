package com.dwaynegrantcodefellowship.codefellowship;

import com.dwaynegrantcodefellowship.codefellowship.models.ApplicationUser;
import com.dwaynegrantcodefellowship.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;


@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/")
    public String getHome(Principal p) {
        if (p != null) {
            System.out.println(p.getName());
        } else {
            System.out.println("Nobody is logged in");
        }
        return "home";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        return new RedirectView("/");
    }
}
