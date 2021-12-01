package com.bank.account.business.operations;

import com.bank.account.entity.Operations;
import com.bank.account.model.OperationModel;

import java.util.List;

public interface OperationsBusiness {

    Operations transaction(OperationModel operationModel);
    Operations withdrawal(OperationModel operationModel);
    List<Operations> findAllOperationByClient(Long idClient);
    List<Operations> findAllOperations();

}
