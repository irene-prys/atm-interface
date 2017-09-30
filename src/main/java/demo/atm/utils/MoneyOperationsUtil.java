package demo.atm.utils;

import demo.atm.domains.Money;
import demo.atm.exceptions.MoneyOperationException;

import java.math.BigDecimal;

public class MoneyOperationsUtil {
    public static Money sub(Money minuendMoney, Money subtrahendMoney) throws MoneyOperationException {
        if (minuendMoney.getAmount().compareTo(subtrahendMoney.getAmount()) < 0) {
            throw new MoneyOperationException("Subtrahend money amount is bigger than minuend. Money can't be negative.");
        }
        if (!minuendMoney.getCurrency().getCurrencyCode().equals(subtrahendMoney.getCurrency().getCurrencyCode())) {
            throw new MoneyOperationException("Currencies are different");
        }

        BigDecimal difference = minuendMoney.getAmount().subtract(subtrahendMoney.getAmount());
        Money moneyResult = new Money();
        moneyResult.setAmount(difference);
        moneyResult.setCurrency(minuendMoney.getCurrency());
        return moneyResult;
    }
}
