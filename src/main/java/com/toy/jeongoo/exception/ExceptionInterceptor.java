package com.toy.jeongoo.exception;

import com.toy.jeongoo.utils.DefaultResponse;
import com.toy.jeongoo.utils.ResponseMessage;
import com.toy.jeongoo.utils.StatusCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultResponse<Map<String, ErrorResponse>> handelValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, ErrorResponse> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(e -> errorMap.put(((FieldError) e).getField(), makeErrorResponse(e.getCode(), e.getDefaultMessage())));
        return DefaultResponse.res(StatusCode.BAD_REQUEST, ResponseMessage.VALUE_VALIDATION_ERROR, errorMap);
    }

    private ErrorResponse makeErrorResponse(String validationCode, String detail) {
        ErrorCode errorCode = ErrorCode.findByValidationCode(validationCode);
        return new ErrorResponse(errorCode.getCode(), errorCode.getDescription(), detail);
    }
}
