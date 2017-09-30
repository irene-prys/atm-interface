package demo.atm.rest;

import demo.atm.domains.Card;
import demo.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> list() {
        return cardService.findAll();
    }

    @PostMapping
    public ResponseEntity<Card> create(Card card) {
        return new ResponseEntity<Card>(cardService.create(card), HttpStatus.OK);// todo: handle exceptions
    }

    @PutMapping("/block")
    public ResponseEntity<Card> updateBlockStatus(String cardNumber, Boolean blockStatus) {
        Card card = cardService.find(cardNumber);
        if (card != null) {
            return new ResponseEntity<>(blockStatus ? cardService.block(cardNumber) : cardService.unblock(cardNumber), HttpStatus.OK);
        }
        return new ResponseEntity<>(card, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Card> delete(@PathVariable("id") long id) {
        Card cardToDelete = cardService.find(id);
        if (cardToDelete != null) {
            cardService.delete(id);
        }
        return new ResponseEntity<>(cardToDelete, cardToDelete == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }


}
