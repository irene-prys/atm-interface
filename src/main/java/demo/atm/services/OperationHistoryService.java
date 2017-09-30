package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.domains.Money;
import demo.atm.domains.OperationHistory;
import demo.atm.domains.OperationType;

import java.util.Date;
import java.util.List;

public interface OperationHistoryService {
    OperationHistory addNewRecord(Card card, OperationType operationType, Date date, Money amount);
    List<OperationHistory> findAll();
}
