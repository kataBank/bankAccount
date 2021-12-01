package com.bank.account.controler.account;

import com.bank.account.business.account.AccountBusiness;
import com.bank.account.entity.Account;
import com.bank.account.mapper.Mapper;
import com.bank.account.model.AccountModel;
import com.bank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountControler {

  @Autowired private AccountBusiness accountBusiness;

  @PostMapping
  public ResponseEntity<Account> save(@RequestBody AccountModel accountModel) {
    return new ResponseEntity<Account>(accountBusiness.save(accountModel), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Account>> findAccounts() {
    return new ResponseEntity<List<Account>>(accountBusiness.findAllAccount(), HttpStatus.ACCEPTED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Account> findAccountByIdClient(@PathVariable Long id) {
    return new ResponseEntity<Account>(
        accountBusiness.findAccountByIdClient(id), HttpStatus.ACCEPTED);
  }
}
