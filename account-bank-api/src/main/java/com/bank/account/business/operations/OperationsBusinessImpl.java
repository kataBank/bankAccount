package com.bank.account.business.operations;

import com.bank.account.entity.Account;
import com.bank.account.entity.Operations;
import com.bank.account.execption.FunctionnalException;
import com.bank.account.mapper.Mapper;
import com.bank.account.model.OperationModel;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.ClientRepository;
import com.bank.account.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class OperationsBusinessImpl implements OperationsBusiness {
  private static final String DEBITOPERATION = "DEBIT";
  @Autowired private AccountRepository accountRepository;
  @Autowired private ClientRepository clientRepository;
  @Autowired private OperationRepository operationRepository;

  @Override
  public Operations transaction(OperationModel operationModel) {

    Account currentAccount = findAccount(operationModel);

    // Controle de solde pour OP Debit
    if (balanceCheck(currentAccount.getBalance(), operationModel.getAmountOperation()) == -1
        && operationModel.getType().equals(DEBITOPERATION)) {
      throw new FunctionnalException("SOLDE_INSUFFISANT", "400");
    }

    Operations operation = Mapper.mapFromModel(operationModel, currentAccount);
    currentAccount.setBalance(operation.getBalance());
    operation.setAccount(currentAccount);
    operation.setBalance(currentAccount.getBalance());

    //Controle Beneficiare
    //if(operationModel.getIdBeneficiary()!=null){
      // findBeneficiary and set it to operation
   // }

    return operationRepository.save(operation);
  }

  @Override
  public Operations withdrawal(OperationModel operationModel) {
    return null;
  }

  private  int balanceCheck(Double accountBalance, Double amountOperation) {
    return accountBalance.compareTo(amountOperation);
  }

  @Override
  public List<Operations> findAllOperationByClient(Long idClient) {
    return null;
  }

  @Override
  public List<Operations> findAllOperations() {
    return operationRepository.findAll();
  }


  private Account findAccount(OperationModel operationModel){
    return  accountRepository
            .findAccountByAccountNumber(operationModel.getAccountNumber())
            .orElseThrow(() -> new FunctionnalException("MISSING_DEPOSIT_ACCOUNT", "400"));
  }

  private Account findBeneficiary(OperationModel operationModel){
    return  accountRepository
            .findAccountByAccountNumber(operationModel.getAccountNumber())
            .orElseThrow(() -> new FunctionnalException("MISSING_DEPOSIT_ACCOUNT", "400"));
  }
}
