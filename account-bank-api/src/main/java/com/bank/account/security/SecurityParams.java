package com.bank.account.security;

public class SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="Gabrielle@102@";
    public static final long EXPIRATION=10*24*3600*1000;
    public static final String HEADER_PREFIX="Bearer ";
}
