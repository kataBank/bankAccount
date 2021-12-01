package com.bank.account.model;


import lombok.Data;
import java.time.LocalDate;
@Data
public class OperationModel {
    private Long idOperation;
    private Double amountOperation;
    private String accountNumber;
    private LocalDate dateOperation;
    private Long idBeneficiary;
    private  String type;
    private String description;

}
