package com.bank.account.execption;

public class FunctionnalException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;

    public FunctionnalException(String code) {
        super();
        this.code = code;
    }

    public FunctionnalException(String message, String code) {
        super(message);
        this.code = code;
    }

    public FunctionnalException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }


    public FunctionnalException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }

    protected FunctionnalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
