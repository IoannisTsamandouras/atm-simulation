package com.exciteholidays.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exciteholidays.common.Errors;
import com.exciteholidays.services.AtmService;

import java.util.Map;

import static com.exciteholidays.common.Attributes.error;
import static com.exciteholidays.common.Attributes.fifty;
import static com.exciteholidays.common.Attributes.twenty;

@Controller("/")
public class MainController {
    @Autowired
    com.exciteholidays.services.AtmService atmService;
    
    @GetMapping
    public String index(Model model) {
    	model.addAttribute("atm", "Total Amount: " + atmService.getTwenties() + 
    			" *$20 +" + atmService.getFifties() + "*$50=" + atmService.getTotalAmount() + "$");
    	return "index";
    }
    
    @PostMapping("/initialize")
    public String initialize(@RequestParam("twenties") Long twenties, @RequestParam("fifties") Long fifties, Model model) {
        atmService.initialize(twenties, fifties);
        model.addAttribute("atm", "Available ATM amount: " + atmService.getTwenties() + 
        		"*20$ + " + atmService.getFifties() + "*50$ = " + atmService.getTotalAmount() + "$");
        return "index";
    }

    @RequestMapping("/allocate")
    public String allocate(@RequestParam("amount") Long amount, Model model) {
        Map<String, Long> result = atmService.allocate(amount);
        model.addAttribute("atm", "Available ATM amount: " + atmService.getTwenties() + 
        		"*20$ + " + atmService.getFifties() + "*50$ = " + atmService.getTotalAmount() + "$");
        if (AtmService.hasError(result)) {
            model.addAttribute(error, Errors.errors(result.get(error)).getMsg());
        } else {
            model.addAttribute(twenty, result.get(twenty));
            model.addAttribute(fifty, result.get(fifty));
        }
        return "index";
    }
}

