package demo.atm.repositories;

import demo.atm.domains.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long>{
    Card findByCardNumber(String cardNumber);
}
