package demo.atm.services;

import demo.atm.domains.Card;
import demo.atm.domains.OperationHistory;
import demo.atm.domains.OperationType;
import demo.atm.repositories.OperationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OperationHistoryServiceImpl implements OperationHistoryService {
    @Autowired
    private OperationHistoryRepository historyRepository;
    @Autowired
    private CardService cardService;

    @Override
    public OperationHistory addNewRecord(Card card, OperationType operationType, Date date) {
        OperationHistory operationHistory = new OperationHistory();
        operationHistory.setCard(card);
        operationHistory.setDate(date);// todo: timezone
        operationHistory.setOperationType(operationType);
        return historyRepository.save(operationHistory);
    }
}
