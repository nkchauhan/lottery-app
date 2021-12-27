package com.lottery.lotteryapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ApiValidation {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiValidation.class);

    private ApiValidation() {
    }

    public static void getConstraintViolationsAndThrowException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        String fieldWithError;
        Object rejectedValue;
        String defaultMessage;
        List<String> errorMessageList = new ArrayList<>();
        for(ConstraintViolation<?> violation : constraintViolations) {
            fieldWithError = violation.getPropertyPath().toString();
            rejectedValue = violation.getInvalidValue();
            defaultMessage = violation.getMessage();
            errorMessageList.add(String.format("Error with field %s. %s. Rejected value is: %s", fieldWithError, defaultMessage, rejectedValue));

        }
        throw new IllegalStateException(errorMessageList.toString());
    }
}
