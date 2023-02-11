package org.example;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class HomeController implements ErrorController {

    @RequestMapping({"/", "/login", "/register"})
    public String home() {
        return "forward:/index.html";
    }


}
