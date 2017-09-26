package demo.atm.utils;

import demo.atm.domains.Card;
import demo.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataInitializer {
    @Autowired
    private CardService cardService;

    public void initCards() {
        cardService.create("1111-1111-1111-1111", "1111");
        cardService.create("1111-2222-3333-4444", "1234");
        cardService.create("2222-2222-2222-2222", "2222");
        cardService.create("1234-5678-9012-3451", "5467");
        cardService.create("5676-8765-9432-3245", "3232");

        //create blocked cards:
        createNewCard("3333-3333-3333-3333", "3333", true, false);
        createNewCard("1234-5678-9012-3456", "1234", true, false);

        //create deleted cards
        createNewCard("5555-5555-5555-5555", "5555", true, false);
    }

    private void createNewCard(String cardNumber, String pinCode, boolean blocked, boolean deleted) {
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setPinCode(pinCode);
        card.setBlocked(blocked);
        card.setDeleted(deleted);
        cardService.create(card);
    }
}
