package com.example.code.test.exception;

public class RestError {

    private final int errorCode;

    private final String quest;

    private final String massage;

    public RestError(int errorCode, String quest, String massage) {
        this.errorCode = errorCode;
        this.quest = quest;
        this.massage = massage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getQuest() {
        return quest;
    }

    public String getMassage() {
        return massage;
    }
}
