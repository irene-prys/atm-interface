package demo.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AtmController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/cancel")
    public String cancel(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }
}
