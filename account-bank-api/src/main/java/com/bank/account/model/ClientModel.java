package com.bank.account.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientModel {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birtDayDate;
    private String phoneNumber;
    private String emailAdress;
    private String accountNumber;
    private String clientId;
}
