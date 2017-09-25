package demo.atm.atm;

import demo.atm.atm.domains.Card;
import demo.atm.atm.services.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceTests {

    @Autowired
    private CardService cardService;

    // creation
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
        assertEquals(pinCode, card.getPinCode());// todo: wrong! need to hash + solid
        assertFalse(card.isBlocked());
        assertFalse(card.isDeleted());
        assertEquals(salt, card.getPinCodeSalt());
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
        assertEquals(pinCode, card.getPinCode());// todo: wrong! need to hash + solid
    }

    @Test(expected = Exception.class)// todo: throw custom exception
    public void shouldNotAllowCreateCardIfItExistsByNumberAndPin() {
        String cardNumber = "1234-5678-9012-3453";
        String pinCode = "3221";
        cardService.create(cardNumber, pinCode);
        cardService.create(cardNumber, pinCode);
    }

    @Test(expected = Exception.class)// todo: throw custom exception
    public void shouldNotAllowCreateCardIfItExists() {
        String cardNumber = "1234-5678-9012-3454";
        String pinCode = "3221";
        Card card = newCard(cardNumber, pinCode);
        cardService.create(card);
        card = newCard(cardNumber, pinCode);
        cardService.create(card);
    }

    // check all not null fields
    // check number has length and numbers inside

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
    }

    //update
    @Test
    public void shouldUpdate() {
        //given
        String cardNumber = "1234-5678-9012-3458";
        String pinCode = "3221";
        Card card = newCard(null, cardNumber, pinCode, "world", false, false);
        cardService.create(card);
        assertNotNull(card.getId());

        cardNumber = "1234-5678-9012-3459";
        pinCode = "3222";
        String salt = "hello";
        Card card2 = newCard(card.getId(), cardNumber, pinCode, salt, true, true);

        //when
        cardService.update(card2);

        //then
        card = cardService.find(card.getId());
        assertEquals(cardNumber, card.getCardNumber());
        assertEquals(pinCode, card.getPinCode());// todo: wrong! need to hash + solid
        assertTrue(card.isBlocked());
        assertTrue(card.isDeleted());
        assertEquals(salt, card.getPinCodeSalt());

    }

    @Test
    public void shouldFindByCardNumber() {
        String cardNumber = "1234-5678-9012-3460";
        String pinCode = "3221";
        Card card = cardService.create(cardNumber, pinCode);

        Card foundCard = cardService.find(cardNumber);
        assertEquals(card.getId(), foundCard.getId());
    }

    //list

    //should store pinCode as hash
    // should verify pinCode of card


    // verify card number
    //verify pin:

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
