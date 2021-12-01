package com.bank.account.business.cleint;

import com.bank.account.entity.Client;
import com.bank.account.model.ClientModel;

import java.util.List;
import java.util.Optional;

public interface ClientBusiness {
    Client save(ClientModel clientModel);
    Client updateClient(ClientModel clientModel);
    Optional<Client> findClientByid(Long idClient);
    List<Client> findClientAll();
}
