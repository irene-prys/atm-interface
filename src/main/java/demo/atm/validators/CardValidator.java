package demo.atm.validators;

import demo.atm.domains.Card;
import demo.atm.utils.PasswordGenerator;

import java.util.Optional;

public class CardValidator {

    public static Optional<String> validateCardNumber(Card card) {
        if (card == null) {
            return Optional.of("Card not found");
        }
        if (card.isDeleted()) {
            return Optional.of("Card with such number is removed");
        }
        if (card.isBlocked()) {
            return Optional.of("Card with such number is blocked");
        }
        return Optional.empty();
    }

    public static boolean isPinValid(Card card, String pinCode) {
        String enteredPin = PasswordGenerator.hashPassword(pinCode, card.getPinCodeSalt());
        return card.getPinCode().equals(enteredPin);
    }

}
