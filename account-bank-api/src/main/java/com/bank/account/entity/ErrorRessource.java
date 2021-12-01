package com.bank.account.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRessource {
    private String code;
    private String message;

}
