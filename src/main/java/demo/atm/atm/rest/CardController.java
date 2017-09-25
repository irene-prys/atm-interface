package demo.atm.atm.rest;

import demo.atm.atm.domains.Card;
import demo.atm.atm.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> list() {
        return cardService.findAll();
    }

    @PostMapping
    public Card create(Card card) {
        return cardService.create(card);// todo: handle exceptions
    }

    @PutMapping
    public ResponseEntity<Card> update(Card card) {
        if (card.getId() == null) {
            return new ResponseEntity<>(card, HttpStatus.BAD_REQUEST);
        }
        Card cardToUpdate = cardService.find(card.getId());
        if (cardToUpdate != null) {
            return new ResponseEntity<>(cardService.update(card), HttpStatus.OK);
        }
        return new ResponseEntity<>(card, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Card> delete(@PathVariable("id") long id) {
        Card cardToDelete = cardService.find(id);// todo: move this logic to service; not sure we cant remove finding
        if (cardToDelete != null) {
            cardService.delete(id);
        }
        return new ResponseEntity<>(cardToDelete, cardToDelete == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }


}
