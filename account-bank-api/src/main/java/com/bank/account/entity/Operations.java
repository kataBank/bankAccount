package com.bank.account.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "OPERATIONS")
public class Operations implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idOperation;

  @Column(name = "DEBIT_AMOUNT_OPERATION")
  private Double debitAmount;

  @Column(name = "CREDIT_AMOUNT_OPERATION")
  private Double creditAmount;

  @Column(name = "BALANCE")
  private Double balance;

  @Column(name = "OPERATION_DATE")
  private LocalDate dateOperation;

  @Column(name = "DESCRIPTION")
  private String description;

  @OneToOne private Beneficiary beneficiary;
  @OneToOne private Account account;

  public Operations() {}

  public Long getIdOperation() {
    return idOperation;
  }

  public void setIdOperation(Long idOperation) {
    this.idOperation = idOperation;
  }

  public Double getDebitAmount() {
    return debitAmount;
  }

  public void setDebitAmount(Double debitAmount) {
    this.debitAmount = debitAmount;
  }

  public Double getCreditAmount() {
    return creditAmount;
  }

  public void setCreditAmount(Double creditAmount) {
    this.creditAmount = creditAmount;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getCreditamount() {
    return creditAmount;
  }

  public void setCreditamount(Double creditamount) {
    this.creditAmount = creditamount;
  }

  public Double getSolde() {
    return balance;
  }

  public void setSolde(Double solde) {
    this.balance = solde;
  }

  public LocalDate getDateOperation() {
    return dateOperation;
  }

  public void setDateOperation(LocalDate dateOperation) {
    this.dateOperation = dateOperation;
  }

  public Beneficiary getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(Beneficiary beneficiary) {
    this.beneficiary = beneficiary;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
