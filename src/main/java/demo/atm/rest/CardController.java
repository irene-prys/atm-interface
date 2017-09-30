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

    @PutMapping("/block/{id}/{blockStatus}")
    public ResponseEntity<Card> updateBlockStatus(@PathVariable("id") Long id,
                                                  @PathVariable("blockStatus") Boolean blockStatus) {
        Card card = cardService.find(id);
        if (card != null) {
            return new ResponseEntity<>(blockStatus ? cardService.block(card.getCardNumber()) :
                    cardService.unblock(card.getCardNumber()), HttpStatus.OK);
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
