package com.teckas.employeeapp.exception;

public class ApplicationException extends Exception{

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ApplicationException() {
    }

    public ApplicationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
