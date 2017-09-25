package demo.atm.atm.services;

import demo.atm.atm.domains.Card;

public interface CardService  {
    Card create(Card card);
    Card create(String cardNumber, String pinCode);
    Card update(Card card);
    Card delete(long cardId);
    Card find(String cardNumber);
    Card find(Long id);
    Card block(String cardNumber);
    Card unblock(String cardNumber);
}
