package com.bank.account.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ROLE_NAME")
  private String roleName;

  @Column(name = "DESCRIPTION")
  private String description;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
