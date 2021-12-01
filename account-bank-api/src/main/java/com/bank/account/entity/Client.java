package com.bank.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "CLIENTS")
public class Client implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "lastName", nullable = false)
  private String lastName;

  @Column(name = "firstName", nullable = false)
  private String firstName;

  @Column(name = "birtDayDate")
  private LocalDate birtDayDate;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "clientId")
  private String clientId;

  @Column(name = "accountNumber")
  private String accountNumber;

  @Column(name = "emailAdress")
  private String emailAdress;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  private boolean status;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Collection<Role> roles = new ArrayList<Role>();

  public Client() {}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public LocalDate getBirtDayDate() {
    return birtDayDate;
  }

  public void setBirtDayDate(LocalDate birtDayDate) {
    this.birtDayDate = birtDayDate;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getEmailAdress() {
    return emailAdress;
  }

  public void setEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Collection<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = roles;
  }
}
