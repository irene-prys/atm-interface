package demo.atm.controllers;

import demo.atm.domains.Card;
import demo.atm.domains.OperationType;
import demo.atm.exceptions.MoneyOperationException;
import demo.atm.services.CardService;
import demo.atm.services.OperationHistoryService;
import demo.atm.validators.WithdrawalValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/withdraw")
public class WithdrawalController {
    @Autowired
    private OperationHistoryService historyService;
    @Autowired
    private CardService cardService;

    @Autowired
    private WithdrawalValidator validator;


    @RequestMapping(value = "/sum", method = RequestMethod.POST)
    public String removeSum(@RequestParam String withdrawAmount, Model model, HttpSession session) {
        String cardNumber = (String) session.getAttribute(AuthenticationController.SESSION_CARD_NUMBER_ATTRIBUTE_NAME);
        if(cardNumber == null) {
            return "index";
        }

        Card card = cardService.find(cardNumber);

        Optional<String> error = validator.validateWithdraw(card.getBalance(), withdrawAmount);
        if (error.isPresent()) {
            model.addAttribute("error", error.get());
            return "withdraw";
        }

        try {
            cardService.withdraw(card, withdrawAmount);
        } catch (MoneyOperationException e) {
            model.addAttribute("error", e.getMessage());
            return "withdraw";
        }

        Date date = new Date();
        historyService.addNewRecord(card, OperationType.WITHDRAW, date, card.getBalance());

        model.addAttribute("cardNumber", cardNumber);
        model.addAttribute("operationTime", date);
        model.addAttribute("currentBalance", card.getBalance().getAmount());
        model.addAttribute("currency", card.getBalance().getCurrency());
        model.addAttribute("withdrawalAmount", withdrawAmount);

        return "operation-report";
    }

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }
}
