package demo.atm.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AtmController {

    @RequestMapping("/")//todo: first screen should be different: redirect to input card if needed
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/card", method = RequestMethod.POST)
    public String sendCreditCardNumber(@RequestParam String atmCardNumber){
        return "pin-code-screen";
    }

    @RequestMapping(value = "/card/pin", method = RequestMethod.POST)
    public String sendPinCode(@RequestParam String pinCode){
        // find card and compare pins
        // if pin not correct count tries
        // --> if tries >=4 => block
        // if ok => open cabinet
        return "cabinet";
    }
}
