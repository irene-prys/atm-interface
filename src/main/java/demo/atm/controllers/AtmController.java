package demo.atm.controllers;

import demo.atm.domains.Card;
import demo.atm.services.CardService;
import demo.atm.validators.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class AtmController {
    private final static String CARD_NUMBER_ATTRIBUTE_NAME = "cardNumber";

    @Autowired
    private CardService cardService;

    @RequestMapping("/")//todo: first screen should be different: redirect to input card if needed
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public String sendCreditCardNumber(@RequestParam String atmCardNumber, Model model, HttpSession session) {
        session.removeAttribute(CARD_NUMBER_ATTRIBUTE_NAME);
        Card card = cardService.find(atmCardNumber);
        Optional<String> validationErrors = CardValidator.validateCardNumber(card);
        if (validationErrors.isPresent()) {
            model.addAttribute("error", validationErrors.get());
            return "card-number-exceprion";
        }

        if (true) {
            session.setAttribute(CARD_NUMBER_ATTRIBUTE_NAME, atmCardNumber);
        }
        return "pin-code-screen";
    }

    @RequestMapping(value = "/card/pin", method = RequestMethod.POST)
    public String sendPinCode(@RequestParam String pinCode, Model model, HttpSession session) {
        Card card = cardService.find((String) session.getAttribute(CARD_NUMBER_ATTRIBUTE_NAME));
        if (!CardValidator.isPinValid(card, pinCode)) {
            cardService.updateTries(card);
            if (card.getPinTries() >= Card.MAX_NUMBER_OF_PIN_TRIES) {
                model.addAttribute("error", "You card is blocked");
            } else {
                model.addAttribute("error", "Pin is incorrect. You made " + card.getPinTries() + " tries of " + Card.MAX_NUMBER_OF_PIN_TRIES);
            }
            return "pin-code-screen";
        }
        return "cabinet";
    }
}
