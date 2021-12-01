package com.bank.account.repository;

import com.bank.account.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.accountNumber=:accountNumber")
    Client findClientByAccountNumber(String accountNumber);

    //@Query("SELECT c FROM Client c WHERE c.accountNumber=:accountNomberOrClientId OR c.clientId=:accountNomberOrClientId")
    //Client findClientByAccountNumberOrClientId(String accountNomberOrClientId);

}
