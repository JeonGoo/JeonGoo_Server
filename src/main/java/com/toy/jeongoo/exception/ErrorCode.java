package com.toy.jeongoo.exception;

import java.util.Arrays;

public enum ErrorCode {
    NOT_NULL("NotNull", "ERROR_CODE_0001", "필수값이 누락되었습니다."),
    NOT_EMPTY("NotEmpty", "ERROR_CODE_0002", "필수값이 비어있습니다."),
    NOT_BLANK("NotBlank", "ERROR_CODE_0003", "필수값이 공백입니다."),
    EMAIL("Email", "ERROR_CODE_0004", "올바른 이메일 형식이 아닙니다.");

    private String validationCode;
    private String code;
    private String description;

    ErrorCode(String validationCode, String code, String description) {
        this.validationCode = validationCode;
        this.code = code;
        this.description = description;
    }

    public static ErrorCode findByValidationCode(String validationCode) {
        return Arrays.stream(values())
                .filter(errorCode -> errorCode.validationCode.equals(validationCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 검증 코드입니다."));
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
