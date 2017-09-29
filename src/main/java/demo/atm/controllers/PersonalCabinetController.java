package demo.atm.controllers;

import demo.atm.domains.Card;
import demo.atm.domains.OperationType;
import demo.atm.services.CardService;
import demo.atm.services.OperationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/cabinet")
public class PersonalCabinetController {
    @Autowired
    private OperationHistoryService historyService;
    @Autowired
    private CardService cardService;

    @RequestMapping("/balance")
    public String balance(Model model, HttpSession session) {
        String cardNumber = (String) session.getAttribute(AuthenticationController.SESSION_CARD_NUMBER_ATTRIBUTE_NAME);
        Card card = cardService.find(cardNumber);
        Date date = new Date();
        historyService.addNewRecord(card, OperationType.BALANCE, date);

        model.addAttribute("cardBalance", card.getBalance().getAmount().toString());
        model.addAttribute("balanceCurrency", card.getBalance().getCurrency().toString());
        model.addAttribute("operationTime", date);

        return "balance";
    }

    @RequestMapping("/withdraw")
    public String withdraw() {
        //todo: add withdraw to history
        return "withdraw";
    }
}
