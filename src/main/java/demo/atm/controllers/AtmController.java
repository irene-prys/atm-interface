package demo.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AtmController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
