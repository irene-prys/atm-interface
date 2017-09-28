package demo.atm.validators;

import demo.atm.domains.Card;
import demo.atm.utils.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationValidator {

    public Optional<String> validateCardNumber(Card card) {
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

    public Optional<String> validatePinCode(Card card, String pinCode) {
        String enteredPin = PasswordGenerator.hashPassword(pinCode, card.getPinCodeSalt());
        if (!card.getPinCode().equals(enteredPin)) {
            return Optional.of("Pin code is invalid");
        }
        return Optional.empty();
    }

}
