package demo.atm.services;

import demo.atm.domains.Card;

import java.util.List;

public interface CardService {
    Card create(Card card);

    Card create(String cardNumber, String pinCode);

    Card update(Card card);

    Card delete(long cardId);

    Card find(String cardNumber);

    Card find(Long id);

    List<Card> findAll();

    Card block(String cardNumber);

    Card unblock(String cardNumber);

    void updatePinCodeTries(Card card);

    void resetPinCodeTries(Card card);
}
