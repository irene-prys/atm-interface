package demo.atm.controllers;

import demo.atm.exceptions.AuthenticationException;
import demo.atm.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    private final static String CARD_NUMBER_ATTRIBUTE_NAME = "cardNumber";

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping("/")//todo: first screen should be different: redirect to input card if needed
    public String index(HttpSession session) {
        return "index";
    }

    @RequestMapping("/cancel")
    public String cancel(HttpSession session) {
        session.removeAttribute(CARD_NUMBER_ATTRIBUTE_NAME);
        return "index";
    }

    @RequestMapping(value = "/card/number", method = RequestMethod.POST)
    public String sendCreditCardNumber(@RequestParam String atmCardNumber, Model model, HttpSession session) {
        session.removeAttribute(CARD_NUMBER_ATTRIBUTE_NAME);

        try {
            authenticationService.findCard(atmCardNumber);
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "card-number-exceprion";
        }

        session.setAttribute(CARD_NUMBER_ATTRIBUTE_NAME, atmCardNumber);
        return "pin-code-screen";
    }

    @RequestMapping(value = "/card/pin", method = RequestMethod.POST)
    public String sendPinCode(@RequestParam String pinCode, Model model, HttpSession session) {
        try {
            authenticationService.findCard((String) session.getAttribute(CARD_NUMBER_ATTRIBUTE_NAME), pinCode);
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "pin-code-screen";
        }
        return "cabinet";
    }

    @RequestMapping(value = {"/*", "/card/*"}, method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }

}
