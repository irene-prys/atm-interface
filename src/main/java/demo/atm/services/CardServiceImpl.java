package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.domains.Money;
import demo.atm.exceptions.MoneyOperationException;
import demo.atm.repositories.CardRepository;
import demo.atm.utils.MoneyOperationsUtil;
import demo.atm.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card create(Card card) {
        card.setPinCodeSalt(generateSalt());
        card.setPinCode(PasswordGenerator.hashPassword(card.getPinCode(), card.getPinCodeSalt()));
        return cardRepository.save(card);
    }

    @Override
    public Card create(String cardNumber, String pinCode) {
        Card card = new Card();
        card.setCardNumber(cardNumber);

        String salt = generateSalt();
        card.setPinCode(PasswordGenerator.hashPassword(pinCode, salt));
        card.setPinCodeSalt(salt);

        return cardRepository.save(card);
    }

    @Override
    public Card delete(long cardId) {
        Card card = cardRepository.findOne(cardId);
        card.setDeleted(true);
        return cardRepository.save(card);
    }

    @Override
    public Card find(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }

    @Override
    public Card find(Long id) {
        return cardRepository.findOne(id);
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card block(String cardNumber) {
        Card card = find(cardNumber);
        card.setBlocked(true);
        card.setPinTries(Card.MAX_NUMBER_OF_PIN_TRIES);
        return cardRepository.save(card);
    }

    @Override
    public Card unblock(String cardNumber) {
        Card card = find(cardNumber);
        card.setBlocked(false);
        card.setPinTries(0);
        return cardRepository.save(card);
    }

    @Override
    public void updatePinCodeTries(Card card) {
        if (card.getPinTries() < Card.MAX_NUMBER_OF_PIN_TRIES) {
            card.setPinTries(card.getPinTries() + 1);
        } else {
            card.setBlocked(true);
        }
        cardRepository.save(card);
    }

    @Override
    public void resetPinCodeTries(Card card) {
        card.setPinTries(0);
        cardRepository.save(card);
    }

    @Override
    public Card withdraw(Card card, String withdrawAmount) throws MoneyOperationException {
        Money withdrawalMoney = new Money();
        withdrawalMoney.setCurrency(card.getBalance().getCurrency());
        withdrawalMoney.setAmount(new BigDecimal(withdrawAmount));
        Money result = MoneyOperationsUtil.sub(card.getBalance(), withdrawalMoney);
        card.setBalance(result);
        return cardRepository.save(card);
    }

    private String generateSalt() {
        return UUID.randomUUID().toString();
    }
}
