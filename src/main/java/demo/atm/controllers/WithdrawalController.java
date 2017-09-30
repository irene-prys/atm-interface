package demo.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/withdraw")
public class WithdrawalController {

    @RequestMapping(value = "/sum", method = RequestMethod.POST)
    public String removeSum(@RequestParam String amount, Model model) {
        // todo: validate sum
        // todo: remove sum
        return "operation-report";
    }
}
