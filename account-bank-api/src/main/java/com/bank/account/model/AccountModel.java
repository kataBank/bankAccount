package com.bank.account.model;
import lombok.Data;

@Data
public class AccountModel {
    private Long idClient;
    private Long idAccount;
    private String rib;
    private String accountNumber;
    private String iban;
    private Double balance;
    private String AccountType;
    private String domiciliation;

}
