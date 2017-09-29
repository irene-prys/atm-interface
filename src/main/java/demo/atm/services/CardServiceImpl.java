package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.repositories.CardRepository;
import demo.atm.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //todo: validate fields, check if exists
        Card card = new Card();
        card.setCardNumber(cardNumber);

        String salt = generateSalt();
        card.setPinCode(PasswordGenerator.hashPassword(pinCode, salt));
        card.setPinCodeSalt(salt);

        return cardRepository.save(card);
    }

    @Override
    public Card delete(long cardId) {
        //todo: handle situation when card not exists
        Card card = cardRepository.findOne(cardId);
        card.setDeleted(true);
        return cardRepository.save(card);
    }

    @Override
    public Card find(String cardNumber) {// todo: make optional?
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
    public Card block(String cardNumber) { // todo: add test
        Card card = find(cardNumber);
        card.setBlocked(true);
        return cardRepository.save(card);
    }

    @Override
    public Card unblock(String cardNumber) {// todo: add test
        Card card = find(cardNumber);
        card.setBlocked(true);
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

    private String generateSalt() {// todo: think over moving to card
        return UUID.randomUUID().toString();
    }
}
