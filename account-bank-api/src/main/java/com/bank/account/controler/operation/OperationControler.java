package com.bank.account.controler.operation;

import com.bank.account.business.operations.OperationsBusiness;
import com.bank.account.entity.Account;
import com.bank.account.entity.Operations;
import com.bank.account.model.OperationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operation")
public class OperationControler {

  @Autowired private OperationsBusiness operationsBusiness;

  @PostMapping
  public ResponseEntity<Operations> deposit(@RequestBody OperationModel operationModel) {
    return new ResponseEntity<Operations>(
        operationsBusiness.transaction(operationModel), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Operations>> findAllOperationClientById() {
    return new ResponseEntity<List<Operations>>(
        operationsBusiness.findAllOperations(), HttpStatus.ACCEPTED);
  }

  @GetMapping("/{accountNumber}")
  public ResponseEntity<List<Operations>> findAllOperationByAccountNumber(
      @PathVariable String accountNumber) {
    return new ResponseEntity<List<Operations>>(
        operationsBusiness.findOperationByAccount(accountNumber), HttpStatus.ACCEPTED);
  }
}
