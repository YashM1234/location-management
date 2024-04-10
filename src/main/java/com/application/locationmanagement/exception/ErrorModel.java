package com.application.locationmanagement.exception;

import lombok.Data;

@Data
public class ErrorModel {
    private String code;
    private String message;
}
