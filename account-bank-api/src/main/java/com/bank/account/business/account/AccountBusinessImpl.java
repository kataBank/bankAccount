package com.bank.account.business.account;

import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import com.bank.account.execption.FunctionnalException;
import com.bank.account.mapper.Mapper;
import com.bank.account.model.AccountModel;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountBusinessImpl implements AccountBusiness {

  @Autowired private AccountRepository accountRepository;
  @Autowired private ClientRepository clientRepository;

  @Override
  public Account save(AccountModel accountModel) {
    Account account;
    try {
      account = Mapper.mapFromModel(accountModel);
      account.setClients(
          clientRepository
              .findClientByAccountNumber(accountModel.getAccountNumber())
              .orElseThrow(
                  () -> new FunctionnalException("MISSING_CLIENT_FOR_THIS_ACCOUNT", "400")));
      if (account.getIdAccount() == null) {
        account.setBalance(0.0);
      }
    } catch (Exception e) {
      throw new FunctionnalException("ERROR_CREATE_ACCOUNT", "400");
    }
    return accountRepository.save(account);
  }

  @Override
  public Account updateClient(AccountModel accountModel) {
    return null;
  }

  @Override
  public Account findAccountByNumber(String accountNumber) {
    return accountRepository
        .findAccountByAccountNumber(accountNumber)
        .orElseThrow(() -> new FunctionnalException("MISSING_ACCOUNT", "400"));
  }

  @Override
  public Account findAccountByIdClient(Long id) {
    Client client =
        clientRepository
            .findById(id)
            .orElseThrow(() -> new FunctionnalException("MISSING_CLIENT_FOR_THIS_ACCOUNT", "400"));
    return accountRepository.findAccountByClients(client);
  }

  @Override
  public List<Account> findAllAccount() {
    return accountRepository.findAll();
  }
}
