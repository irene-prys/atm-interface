package demo.atm;

import demo.atm.domains.Card;
import demo.atm.exceptions.AuthenticationException;
import demo.atm.services.AuthenticationService;
import demo.atm.services.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {
    @Autowired
    private CardService cardService;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void shouldIncrementTries() throws AuthenticationException {
        String cardNumber = "2222-2222-2222-0000";
        Card card = cardService.create(newCard(cardNumber, "2234"));
        assertEquals(0, card.getPinTries());
        assertFalse(card.isBlocked());
        try {
            authenticationService.findCardAndCheckPinCode(cardNumber, "1111");
        } catch (AuthenticationException e) {
            // do nothing
        }
        card = cardService.find(card.getId());
        assertEquals(1, card.getPinTries());
        assertFalse(card.isBlocked());
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionIfCardDeleted() throws AuthenticationException {
        Card card = newCard("2222-2222-2222-0001", "2234");
        card.setDeleted(true);
        cardService.create(card);
        authenticationService.findCardAndCheckPinCode("", "1111");
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionIfCardBlocked() throws AuthenticationException {
        Card card = newCard("2222-2222-2222-0002", "2234");
        card.setBlocked(true);
        cardService.create(card);
        authenticationService.findCardAndCheckPinCode("", "1111");
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionWhenTriesToFindByPinCodeAndCardNumberIsEmptyString() throws AuthenticationException {
        authenticationService.findCardAndCheckPinCode("", "1111");
    }

    @Test(expected = AuthenticationException.class)
    public void shouldThrowExceptionWhenTriesToFindByPinCodeAndCardNumberIsNull() throws AuthenticationException {
        authenticationService.findCardAndCheckPinCode(null, "1111");
    }

    @Test
    public void shouldBlockCardAfterThresholdTries() {
        String cardNumber = "2222-2222-2222-0001";
        Card card = newCard(cardNumber, "2234");
        card.setPinTries(Card.MAX_NUMBER_OF_PIN_TRIES);
        card = cardService.create(card);
        assertEquals(Card.MAX_NUMBER_OF_PIN_TRIES, card.getPinTries());
        assertFalse(card.isBlocked());
        try {
            authenticationService.findCardAndCheckPinCode(cardNumber, "1111");
        } catch (AuthenticationException e) {
            // do nothing
        }
        card = cardService.find(card.getId());
        assertEquals(Card.MAX_NUMBER_OF_PIN_TRIES, card.getPinTries());
        assertTrue(card.isBlocked());
    }

    private Card newCard(Long id, String cardNumber,
                         String pinCode, String salt,
                         boolean blocked, boolean deleted) {
        Card card = new Card();
        card.setId(id);
        card.setCardNumber(cardNumber);
        card.setPinCode(pinCode);
        card.setPinCodeSalt(salt);
        card.setBlocked(blocked);
        card.setDeleted(deleted);
        return card;
    }

    private Card newCard(String cardNumber,
                         String pinCode) {
        return newCard(null, cardNumber, pinCode, "hello", false, false);
    }

}
