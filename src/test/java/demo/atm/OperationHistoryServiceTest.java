package demo.atm;

import demo.atm.domains.Card;
import demo.atm.domains.Money;
import demo.atm.domains.OperationHistory;
import demo.atm.domains.OperationType;
import demo.atm.services.CardService;
import demo.atm.services.OperationHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationHistoryServiceTest {
    @Autowired
    private CardService cardService;
    @Autowired
    private OperationHistoryService historyService;

    @Test
    public void shouldAddNewRecord() {
        Card card = newCard("9999-9999-9999-0000", "1234");
        cardService.create(card);
        OperationHistory historyRecord = historyService.addNewRecord(card, OperationType.BALANCE, new Date(), card.getBalance());
        Optional<OperationHistory> foundHistory = historyService.findAll().stream().filter(h -> h.getId() == historyRecord.getId()).findFirst();
        assertTrue(foundHistory.isPresent());
        assertTrue(foundHistory.get().getCard().getCardNumber().equals(card.getCardNumber()));
        assertTrue(foundHistory.get().getOperationType() == OperationType.BALANCE);
        assertTrue(foundHistory.get().getAmount().getAmount().compareTo(card.getBalance().getAmount()) == 0);
        assertEquals(card.getBalance().getCurrency().getCurrencyCode(), foundHistory.get().getAmount().getCurrency().getCurrencyCode());
        assertNotNull(foundHistory.get().getDate());
    }

    @Test
    public void shouldFindAllRecords() {
        Card card = newCard("9999-9999-9999-0001", "1234");
        cardService.create(card);
        OperationHistory historyRecord1 = historyService.addNewRecord(card, OperationType.BALANCE, new Date(), card.getBalance());

        Card card2 = newCard("9999-9999-9999-0002", "1234");
        cardService.create(card2);
        OperationHistory historyRecord2 = historyService.addNewRecord(card2, OperationType.WITHDRAW, new Date(),
                new Money(new BigDecimal("200"), Currency.getInstance("USD")));


        List<OperationHistory> histories = historyService.findAll();
        assertTrue(histories.size() >= 2);

        List<OperationHistory> foundHistories = histories.stream().filter(h -> h.getId() == historyRecord1.getId() ||
                h.getId() == historyRecord2.getId()).collect(Collectors.toList());
        assertTrue(foundHistories.size() == 2);
    }


    private Card newCard(String cardNumber,
                         String pinCode) {
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setPinCode(pinCode);
        Money balance = new Money(new BigDecimal("2000"), Currency.getInstance("USD"));
        card.setBalance(balance);
        return card;
    }
}
