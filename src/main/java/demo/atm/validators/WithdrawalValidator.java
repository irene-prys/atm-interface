package demo.atm.validators;

import demo.atm.domains.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WithdrawalValidator {
    public Optional<String> validateWithdraw(Money balance, String withdrawalAmount) {
        if (balance.getAmount().compareTo(new BigDecimal(withdrawalAmount)) < 0) {
            return Optional.of("Withdrawal amount is bigger than balance amount. Operation denied.");//todo: add i18n
        }
        return Optional.empty();
    }

}
