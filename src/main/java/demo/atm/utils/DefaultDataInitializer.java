package demo.atm.utils;

import demo.atm.domains.Balance;
import demo.atm.domains.Card;
import demo.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class DefaultDataInitializer {
    @Autowired
    private CardService cardService;

    public void initCards() {
        createNewActiveCard("1111-1111-1111-1111", "1111", "1000");
        createNewActiveCard("1111-2222-3333-4444", "1234", "200");
        createNewActiveCard("2222-2222-2222-2222", "2222", "20000");
        createNewActiveCard("1234-5678-9012-3451", "5467", "300");
        createNewActiveCard("5676-8765-9432-3245", "3232", "600");

        //create blocked cards:
        createNewCard("3333-3333-3333-3333", "3333", true, false, "300");
        createNewCard("1234-5678-9012-3456", "1234", true, false, "250");

        //create deleted cards
        createNewCard("5555-5555-5555-5555", "5555", true, false, "1000");
    }

    private void createNewCard(String cardNumber, String pinCode, boolean blocked, boolean deleted, String amount) {
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setPinCode(pinCode);
        card.setBlocked(blocked);
        card.setDeleted(deleted);

        Balance balance = new Balance();
        balance.setCurrency(Currency.getInstance("USD"));
        balance.setAmount(new BigDecimal(amount));
        card.setBalance(balance);
        cardService.create(card);
    }

    private void createNewActiveCard(String cardNumber, String pinCode, String amount) {
        createNewCard(cardNumber, pinCode, false, false, amount);
    }
}
