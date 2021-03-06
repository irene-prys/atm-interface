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
@RequestMapping("/card")
public class AuthenticationController {
    public final static String SESSION_CARD_NUMBER_ATTRIBUTE_NAME = "cardNumber";// think over moving to a more appropriate place

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/number", method = RequestMethod.POST)
    public String sendCreditCardNumber(@RequestParam String atmCardNumber, Model model, HttpSession session) {
        session.removeAttribute(SESSION_CARD_NUMBER_ATTRIBUTE_NAME);

        try {
            authenticationService.findCard(atmCardNumber);
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "card-number-exceprion";
        }

        session.setAttribute(SESSION_CARD_NUMBER_ATTRIBUTE_NAME, atmCardNumber);
        return "pin-code-screen";
    }

    @RequestMapping(value = "/pin", method = RequestMethod.POST)
    public String sendPinCode(@RequestParam String pinCode, Model model, HttpSession session) {
        String cardNumber = (String) session.getAttribute(SESSION_CARD_NUMBER_ATTRIBUTE_NAME);
        if (cardNumber == null) {
            return "index";
        }

        try {
            authenticationService.findCardAndCheckPinCode(cardNumber, pinCode);
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "pin-code-screen";
        }
        return "operations";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }
}
