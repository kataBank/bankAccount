package com.bank.account.business.account;

import com.bank.account.entity.Account;
import com.bank.account.model.AccountModel;

import java.util.List;

public interface AccountBusiness {
  Account save(AccountModel accountModel);

  Account updateClient(AccountModel accountModel);

  Account findAccountByNumber(String accountNumber);

  Account findAccountByIdClient(Long id);

  List<Account> findAllAccount();
}
