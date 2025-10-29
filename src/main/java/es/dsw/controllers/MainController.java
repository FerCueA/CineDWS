package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/step4")
    public String step4() {
        return "views/step4";
    }

    @GetMapping("/end")
    public String endGet() {
        return "views/end";
    }

   
}
