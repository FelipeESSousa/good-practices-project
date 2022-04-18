package br.com.goodpractices.resources.handlers.enums;

public enum ExceptionsMessagesEnum {

    INTERNAL_ERROR("Something going wrong!");

    private String message;

    ExceptionsMessagesEnum(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return this.message;
    }
}
