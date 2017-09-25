package demo.atm.atm.services;

import demo.atm.atm.repositories.OperationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationHistoryServiceImpl implements OperationHistoryService {
    @Autowired
    private OperationHistoryRepository operationHistoryRepository;
}
