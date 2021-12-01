package com.bank.account.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAccount;

  @OneToOne private Client clients;

  @Column(name = "RIB", nullable = false, unique = true)
  private String rib;

  @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
  private String accountNumber;

  @Column(name = "IBAN", nullable = false, unique = true)
  private String iban;

  @Column(name = "BALANCE")
  private Double balance;

  @Column(name = "ACCOUNT_TYPE")
  private String AccountType;

  @Column(name = "DOMICILIATION")
  private String domiciliation;

  public Account() {}

  public Long getIdAccount() {
    return idAccount;
  }

  public void setIdAccount(Long idAccount) {
    this.idAccount = idAccount;
  }

  public Client getClients() {
    return clients;
  }

  public void setClients(Client clients) {
    this.clients = clients;
  }

  public String getRib() {
    return rib;
  }

  public void setRib(String rib) {
    this.rib = rib;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public String getAccountType() {
    return AccountType;
  }

  public void setAccountType(String accountType) {
    AccountType = accountType;
  }

  public String getDomiciliation() {
    return domiciliation;
  }

  public void setDomiciliation(String domiciliation) {
    this.domiciliation = domiciliation;
  }
}
