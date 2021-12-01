package com.bank.account.controler.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/operation")
    public String operationView() {
        return "index.html";
    }

    @GetMapping("/client")
    public String clientView() {
        return "index.html";
    }

    @GetMapping("/account")
    public String accountView() {
        return "index.html";
    }

    @GetMapping("/login")
    public String loginView() {
        return "index.html";
    }
}
