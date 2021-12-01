package com.bank.account.entity;

import javax.persistence.*;

@Entity
@Table(name = "BENEFICIARY")
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBeneficiary;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "firstname" ,nullable = false)
    private String firstname;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "rib" ,unique = true, nullable = false)
    private String rib;

    public Long getIdBeneficiary() {
        return idBeneficiary;
    }

    public void setIdBeneficiary(Long idBeneficiary) {
        this.idBeneficiary = idBeneficiary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
