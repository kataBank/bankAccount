package com.bank.account.repository;

import com.bank.account.entity.Account;
import com.bank.account.entity.Operations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OperationRepository extends JpaRepository<Operations, Long> {
    List<Operations>findOperationByAccount(Account account);
}
