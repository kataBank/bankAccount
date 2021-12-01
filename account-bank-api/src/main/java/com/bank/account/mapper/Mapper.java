package com.bank.account.mapper;

import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import com.bank.account.entity.Operations;
import com.bank.account.execption.FunctionnalException;
import com.bank.account.model.AccountModel;
import com.bank.account.model.ClientModel;
import com.bank.account.model.OperationModel;

import java.time.LocalDate;

public class Mapper {

  private static final String DEBITOPERATION = "DEBIT";
  private static final String CREDITOPERATION = "CREDIT";

  public static Client mapFromModel(ClientModel clientModel) {
    Client client = new Client();
    client.setLastName(clientModel.getLastName());
    client.setFirstName(clientModel.getFirstName());
    client.setBirtDayDate(clientModel.getBirtDayDate());
    if (clientModel.getId() != null) {
      client.setId(clientModel.getId());
    }
    return client;
  }

  public static Account mapFromModel(AccountModel accountModel) {
    Account account = new Account();
    account.setAccountNumber(accountModel.getAccountNumber());
    account.setAccountType(accountModel.getAccountType());
    account.setBalance(accountModel.getBalance());
    account.setDomiciliation(accountModel.getDomiciliation());
    account.setIban(accountModel.getIban());
    account.setRib(accountModel.getRib());
    return account;
  }

  public static Operations mapFromModel(OperationModel operationModel, Account account) {
    Operations operation = new Operations();
    operation.setDateOperation(LocalDate.now());
    operation.setDescription(operationModel.getDescription());
    if (operationModel.getType().equals(DEBITOPERATION)) {
      operation.setDebitAmount(operationModel.getAmountOperation());
      operation.setCreditAmount(Double.valueOf(0.0));
      operation.setBalance(Double.sum(account.getBalance(), -operationModel.getAmountOperation()));
    } else if (operationModel.getType().equals(CREDITOPERATION)) {
      operation.setCreditamount(operationModel.getAmountOperation());
      operation.setDebitAmount(Double.valueOf(0.0));
      operation.setBalance(Double.sum(account.getBalance(), operationModel.getAmountOperation()));
    }
    return operation;
  }
}
