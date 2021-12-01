package com.bank.account.security;

import com.bank.account.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

public class CustomUser extends User {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String lastName;

  private String firstName;

  private LocalDate birtDayDate;

  private String phoneNumber;

  private String clientId;

  private String accountNumber;

  private String emailAdress;

  private boolean status;

  private Role role;

  public CustomUser(
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      Long id) {
    super(username, password, authorities);
    this.id = id;
  }

  public CustomUser(
      String username,
      String password,
      boolean enabled,
      boolean accountNonExpired,
      boolean credentialsNonExpired,
      boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities,
      Long id) {
    super(
        username,
        password,
        enabled,
        accountNonExpired,
        credentialsNonExpired,
        accountNonLocked,
        authorities);
    this.id = id;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public CustomUser(
      Long id,
      String username,
      String password,
      Collection<Role> roles,
      String lastName,
      String firstname,
      String email,
      String phone,
      String clientId,
      LocalDate birtDayDate,
      Collection<? extends GrantedAuthority> authorities,
      String emailAdress) {
    super(username, password, authorities);
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstname;
    this.phoneNumber = phone;
    this.clientId = clientId;
    this.birtDayDate = birtDayDate;
    this.emailAdress = emailAdress;
    this.accountNumber = username;
  }
}
