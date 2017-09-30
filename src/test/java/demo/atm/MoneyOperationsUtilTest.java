package demo.atm;

import demo.atm.domains.Money;
import demo.atm.exceptions.MoneyOperationException;
import demo.atm.utils.MoneyOperationsUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertTrue;

public class MoneyOperationsUtilTest {
    @Test
    public void shouldCalculateSubtraction() throws MoneyOperationException {
        Money m1 = new Money();
        m1.setAmount(new BigDecimal("1000"));
        m1.setCurrency(Currency.getInstance("USD"));
        Money m2 = new Money();
        m2.setAmount(new BigDecimal("200"));
        m2.setCurrency(Currency.getInstance("USD"));
        Money result = MoneyOperationsUtil.sub(m1, m2);
        assertTrue(result.getAmount().compareTo(new BigDecimal("800")) == 0);
    }

    @Test(expected = MoneyOperationException.class)
    public void shouldThrowExceptionIfCurrenciesAreDifferentInSubtraction() throws MoneyOperationException {
        Money m1 = new Money();
        m1.setAmount(new BigDecimal("1000"));
        m1.setCurrency(Currency.getInstance("USD"));
        Money m2 = new Money();
        m2.setAmount(new BigDecimal("200"));
        m2.setCurrency(Currency.getInstance("UAH"));
        MoneyOperationsUtil.sub(m1, m2);
    }

    @Test(expected = MoneyOperationException.class)
    public void shouldThrowExceptionIfSubtrahendBiggerInSubtraction() throws MoneyOperationException {
        Money m1 = new Money();
        m1.setAmount(new BigDecimal("1000"));
        m1.setCurrency(Currency.getInstance("USD"));
        Money m2 = new Money();
        m2.setAmount(new BigDecimal("2000"));
        m2.setCurrency(Currency.getInstance("UAH"));
        MoneyOperationsUtil.sub(m1, m2);
    }
}
