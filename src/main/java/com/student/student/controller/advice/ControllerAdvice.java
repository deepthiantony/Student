package com.student.student.controller.advice;

import com.student.student.exception.StudentServiceException;
import com.student.student.model.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvice.class);

    @ExceptionHandler(StudentServiceException.class)
    public ResponseEntity<ErrorInfo> studentServiceExceptionHandler(StudentServiceException studentServiceException) {
        LOGGER.error(studentServiceException.getMessage(),studentServiceException);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(studentServiceException.getMessage());
        return ResponseEntity.badRequest().body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> ExceptionHandler(Exception exception){
        LOGGER.error(exception.getMessage(),exception);
        ErrorInfo errorInfo=new ErrorInfo();
        errorInfo.setMessage("Some error occured , please contact admin");
        return ResponseEntity.badRequest().body(errorInfo);
    }
}
