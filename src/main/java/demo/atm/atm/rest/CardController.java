package demo.atm.atm.rest;

import demo.atm.atm.domains.Card;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @GetMapping
    public List<Card> list(){
        return null; //todo: implement method
    }

    @PostMapping("/init")
    public List<Card> generate(){
        return null; //todo: implement method
    }

    @PostMapping
    public Card create(){
        return null; //todo: implement method
    }

    @PutMapping
    public Card update(){
        return null; //todo: implement method
    }

    @DeleteMapping
    public Card delete(){
        return null; //todo: implement method
    }

}
