package com.bank.account.business.cleint;

import com.bank.account.entity.Client;
import com.bank.account.execption.FunctionnalException;
import com.bank.account.mapper.Mapper;
import com.bank.account.model.ClientModel;
import com.bank.account.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientBusinessImp implements ClientBusiness {
  @Autowired private ClientRepository clientRepository;
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Client save(ClientModel clientModel) {
    Client client = Mapper.mapFromModel(clientModel);
    client.setPassword(bCryptPasswordEncoder.encode(client.getClientId()));
    return clientRepository.save(client);
  }

  @Override
  public Client updateClient(ClientModel clientModel) {
    return clientRepository.save(Mapper.mapFromModel(clientModel));
  }

  @Override
  public Optional<Client> findClientByid(Long idClient) {
    return clientRepository.findById(idClient);
  }

  @Override
  public List<Client> findClientAll() {
    return clientRepository.findAll();
  }

  @Override
  public Client findClientByAccountNumber(String accountNumber) {
    return clientRepository
        .findClientByAccountNumber(accountNumber)
        .orElseThrow(() -> new FunctionnalException("MISSING_CLIENT_FOR_THIS_ACCOUNT", "400"));
  }
}
