package com.application.locationmanagement.exception;

import com.application.locationmanagement.constant.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handelBusinessException(BusinessException be){
        for(ErrorModel errorModel :be.getErrorList()) {
            logger.debug("Inside handelBusinessException: {}, {}", errorModel.getCode(), errorModel.getMessage());
        }
        ResponseEntity<List<ErrorModel>> responseEntity
                = new ResponseEntity<>(be.getErrorList(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorModel>> handelAllException(Exception ex){

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(ErrorType.UNKNOWN_SERVER_ERROR.toString());
        errorModel.setMessage("Unknown error occurred!");

        logger.debug("Inside handelAllException: {}, {}", errorModel.getCode(), errorModel.getMessage());
        errorModelList.add(errorModel);

        logger.debug("Inside handelAllException: {}", errorModel.getMessage());
        ResponseEntity<List<ErrorModel>> responseEntity
                = new ResponseEntity<>(errorModelList, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
