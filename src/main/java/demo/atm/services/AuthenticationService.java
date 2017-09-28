package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.exceptions.AuthenticationException;

public interface AuthenticationService {
    Card findCard(String cardNumber, String pinCode) throws AuthenticationException;

    Card findCard(String cardNumber) throws AuthenticationException;
}
