package demo.atm;

import demo.atm.domains.Card;
import demo.atm.services.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceTests {

    @Autowired
    private CardService cardService;

    @Test
    public void shouldCreateCardAndThenFindItById() {
        String cardNumber = "1234-5678-9012-3450";
        String pinCode = "3221";
        String salt = "world";
        Card card = newCard(null, cardNumber, pinCode, salt, false, false);
        cardService.create(card);
        assertNotNull(card.getId());

        card = cardService.find(card.getId());
        assertEquals(cardNumber, card.getCardNumber());
        assertFalse(card.isBlocked());
        assertFalse(card.isDeleted());
        assertNotNull(card.getPinCodeSalt());
        assertNotNull(card.getPinCode());
        assertNotEquals("", card.getPinCode());
        assertNotEquals(pinCode, card.getPinCode());
    }

    @Test
    public void shouldCreateCardMarkedBlockedAndDeleted() {
        String cardNumber = "1234-5678-9012-3451";
        String pinCode = "3221";
        Card card = newCard(null, cardNumber, pinCode, "world", true, true);
        cardService.create(card);
        assertNotNull(card.getId());

        card = cardService.find(card.getId());
        assertTrue(card.isBlocked());
        assertTrue(card.isDeleted());
    }

    @Test
    public void shouldCreateCardOnlyByNumberAndPin() {
        String cardNumber = "1234-5678-9012-3452";
        String pinCode = "3221";
        Card card = cardService.create(cardNumber, pinCode);
        assertNotNull(card.getId());

        card = cardService.find(card.getId());
        assertEquals(cardNumber, card.getCardNumber());
        assertNotNull(card.getPinCode());
        assertNotEquals(pinCode, card.getPinCode());
        assertNotEquals("", card.getPinCode());
    }

    @Test(expected = Exception.class)
    public void shouldNotAllowCreateCardIfItExistsByNumberAndPin() {
        String cardNumber = "1234-5678-9012-3453";
        String pinCode = "3221";
        cardService.create(cardNumber, pinCode);
        cardService.create(cardNumber, pinCode);
    }

    @Test(expected = Exception.class)
    public void shouldNotAllowCreateCardIfItExists() {
        String cardNumber = "1234-5678-9012-3454";
        String pinCode = "3221";
        cardService.create(cardNumber, pinCode);
        cardService.create(cardNumber, pinCode);
    }

    @Test
    public void shouldMarkAsDeleted() {
        String cardNumber = "1234-5678-9012-3455";
        String pinCode = "3221";
        Card card = cardService.create(cardNumber, pinCode);
        card = cardService.find(card.getId());
        assertFalse(card.isDeleted());

        cardService.delete(card.getId());
        card = cardService.find(card.getId());
        assertTrue(card.isDeleted());
    }

    @Test
    public void shouldBlock() {
        String cardNumber = "1234-5678-9012-3456";
        String pinCode = "3221";
        Card card = cardService.create(cardNumber, pinCode);
        card = cardService.find(card.getId());
        assertFalse(card.isBlocked());

        cardService.block(card.getCardNumber());
        card = cardService.find(card.getId());
        assertTrue(card.isBlocked());
        assertEquals(Card.MAX_NUMBER_OF_PIN_TRIES, card.getPinTries());
    }

    @Test
    public void shouldUnBlock() {
        String cardNumber = "1234-5678-9012-3457";
        String pinCode = "3221";
        Card card = newCard(null, cardNumber, pinCode, "world", true, false);
        cardService.create(card);
        card = cardService.find(card.getId());
        assertTrue(card.isBlocked());

        cardService.unblock(card.getCardNumber());
        card = cardService.find(card.getId());
        assertFalse(card.isBlocked());
        assertEquals(0, card.getPinTries());
    }

    @Test
    public void shouldFindByCardNumber() {
        String cardNumber = "1234-5678-9012-3460";
        String pinCode = "3221";
        Card card = cardService.create(cardNumber, pinCode);

        Card foundCard = cardService.find(cardNumber);
        assertEquals(card.getId(), foundCard.getId());
    }

    @Test
    public void shouldGenerateSaltIgnoringPreset() {
        String salt = "world";
        Card card = newCard(null, "1234-5678-9012-3461", "2234", salt, false, false);
        cardService.create(card);
        assertNotEquals(salt, card.getPinCodeSalt());
        assertNotNull(card.getPinCodeSalt());
        assertNotEquals("", card.getPinCodeSalt());
    }

    @Test
    public void shouldGenerateSalt() {
        Card card = cardService.create("1234-5678-9012-3462", "2234");
        assertNotNull(card.getPinCodeSalt());
        assertNotEquals("", card.getPinCodeSalt());
    }

    @Test
    public void shouldFindAllCards() {
        //given
        Card card1 = cardService.create("1234-5678-9012-3463", "2234");
        Card card2 = cardService.create("1234-5678-9012-3464", "2234");
        Card card3 = cardService.create("1234-5678-9012-3465", "2234");

        Card cardBlocked = newCard("1234-5678-9012-3466", "2234");
        cardBlocked.setBlocked(true);
        cardService.create(cardBlocked);

        Card cardDeleted = newCard("1234-5678-9012-3467", "2234");
        cardDeleted.setDeleted(true);
        cardService.create(cardDeleted);

        List <String>cardNumbers = Arrays.asList(card1.getCardNumber(), card2.getCardNumber(),
                card3.getCardNumber(), cardBlocked.getCardNumber(), cardDeleted.getCardNumber());

        //when
        List <Card>foundCards = cardService.findAll();

        //then
        assertTrue(cardNumbers.size() <= foundCards.size());
        assertTrue(foundCards.stream().filter(c->cardNumbers.contains(c.getCardNumber())).count() == cardNumbers.size());
    }

    @Test(expected = Exception.class)
    public void shouldNotAllowCreateCardWithoutCardNumber() {
        String pinCode = "3221";
        cardService.create(null, pinCode);
    }

    @Test(expected = Exception.class)
    public void shouldNotAllowCreateCardWithoutPinCode() {
        String cardNumber = "1234-5678-9012-3454";
        cardService.create(cardNumber, null);
    }

    @Test
    public void shouldBlockCard() {
        Card card = cardService.create("1111-1111-1111-0000", "1234");
        assertFalse(card.isBlocked());
        cardService.block(card.getCardNumber());
        card = cardService.find(card.getCardNumber());
        assertTrue(card.isBlocked());
    }

    @Test
    public void shouldUnblockCard() {
        Card card = newCard("1111-1111-1111-0001", "1234");
        card.setBlocked(true);
        cardService.create(card);
        assertTrue(card.isBlocked());

        cardService.unblock(card.getCardNumber());
        card = cardService.find(card.getCardNumber());
        assertFalse(card.isBlocked());
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
