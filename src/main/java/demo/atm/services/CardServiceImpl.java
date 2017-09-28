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
    public Card update(Card card) {
        //todo: handle when card not exists
        return cardRepository.save(card);
    }

    @Override
    public Card delete(long cardId) {
        //todo: handle when card not exists
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
    public Card block(String cardNumber) {
        return block(cardNumber, true);
    }

    @Override
    public Card unblock(String cardNumber) {
        return block(cardNumber, false);
    }

    private Card block(String cardNumber, boolean blockMarker) {
        //todo: handle when card not exists
        Card card = find(cardNumber);
        card.setBlocked(blockMarker);
        return cardRepository.save(card);
    }

    @Override
    public void updateTries(Card card) {// todo: rename
        if (card.getPinTries() < Card.MAX_NUMBER_OF_PIN_TRIES) {
            card.setPinTries(card.getPinTries() + 1);
        } else {
            card.setBlocked(true);
        }
        cardRepository.save(card);
    }

    @Override
    public void resetTries(Card card) {
        card.setPinTries(0);
        cardRepository.save(card);
    }

    private String generateSalt() {// todo: think over moving to card
        return UUID.randomUUID().toString();
    }
}
