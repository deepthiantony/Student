package com.student.student.exception;

public class StudentServiceException extends Exception {
    public StudentServiceException(String message){
        super(message);
    }

    public StudentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
