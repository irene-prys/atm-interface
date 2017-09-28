package demo.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AtmController {
    public final static String SESSION_CARD_NUMBER_ATTRIBUTE_NAME = "cardNumber";

    @RequestMapping("/")//todo: first screen should be different: redirect to input card if needed
    public String index() {
        return "index";
    }

    @RequestMapping("/cancel")
    public String cancel(HttpSession session) {
        session.removeAttribute(SESSION_CARD_NUMBER_ATTRIBUTE_NAME);
        return "index";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }
}
