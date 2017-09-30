package demo.atm.rest;

import demo.atm.domains.OperationHistory;
import demo.atm.services.OperationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/history")
public class HistoryController {
    @Autowired
    private OperationHistoryService historyService;

    @GetMapping
    public List<OperationHistory> list() {
        return historyService.findAll();
    }
}
