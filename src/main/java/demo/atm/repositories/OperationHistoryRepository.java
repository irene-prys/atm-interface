package demo.atm.repositories;

import demo.atm.domains.OperationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long> {
}
