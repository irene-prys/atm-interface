package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.exceptions.AuthenticationException;
import demo.atm.validators.AuthenticationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private CardService cardService;
    @Autowired
    private AuthenticationValidator authenticationValidator;

    @Override
    public Card findCardAndCheckPinCode(String cardNumber, String pinCode) throws AuthenticationException {
        if (cardNumber == null || cardNumber.isEmpty()) {
            throw new AuthenticationException("Card number is not defined");//todo: add i18n
        }

        Card card = cardService.find(cardNumber);
        validateCardAndPinCode(card, pinCode);

        cardService.resetPinCodeTries(card);
        return card;
    }

    @Override
    public Card findCard(String cardNumber) throws AuthenticationException {
        Card card = cardService.find(cardNumber);
        Optional<String> validationError = authenticationValidator.validateCard(card);
        if (validationError.isPresent()) {
            throw new AuthenticationException(validationError.get());//todo: add i18n
        }
        return card;
    }

    private void validateCardAndPinCode(Card card, String pinCode) throws AuthenticationException {
        Optional<String> cardValidationError = authenticationValidator.validateCard(card);
        if (cardValidationError.isPresent()) {
            throw new AuthenticationException(cardValidationError.get());
        }

        Optional<String> validationError = authenticationValidator.validatePinCode(card, pinCode);
        if (validationError.isPresent()) {
            cardService.updatePinCodeTries(card);
            if (card.getPinTries() >= Card.MAX_NUMBER_OF_PIN_TRIES) {
                throw new AuthenticationException("You card is blocked");//todo: add i18n
            } else {
                throw new AuthenticationException("Pin is incorrect. You made " + card.getPinTries() + " tries of " + Card.MAX_NUMBER_OF_PIN_TRIES);//todo: add i18n
            }
        }
    }

}
