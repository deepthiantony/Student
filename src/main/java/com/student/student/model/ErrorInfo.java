package com.student.student.model;

import java.util.Date;

public class ErrorInfo {
    private String message;
    private Date dateTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public ErrorInfo() {
    }
}
