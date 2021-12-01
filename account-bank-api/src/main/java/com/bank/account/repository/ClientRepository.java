package com.bank.account.repository;

import com.bank.account.entity.Client;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.accountNumber=:accountNumber")
    Optional<Client> findClientByAccountNumber(String accountNumber);

    //@Query("SELECT c FROM Client c WHERE c.accountNumber=:accountNomberOrClientId OR c.clientId=:accountNomberOrClientId")
    //Client findClientByAccountNumberOrClientId(String accountNomberOrClientId);

}
