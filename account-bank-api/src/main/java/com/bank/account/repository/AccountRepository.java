package com.bank.account.repository;

import com.bank.account.entity.Account;
import com.bank.account.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByClients(Client client);
    Optional<Account> findAccountByAccountNumber(String accountNumber);
}
