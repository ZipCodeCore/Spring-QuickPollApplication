package io.zipcoder.tc_spring_poll_application.error;

import javax.validation.Validation;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
    String title;   //a brief title of the error condition, eg: "Validation Failure" or "Internal Server Error"
    Integer status; //the HTTP status code for the current request; redundant but useful for client-side error handling
    String detail;  //A short, human-readable description of the error that may be presented to a user
    long timeStamp;  //the time in milliseconds when the error occurred
    String developerMessage;  //detailed information such as exception class name or a stack trace useful for developers to debug
    Map<String, List<ValidationError>> errors;

    public ErrorDetail(String title, Integer status, String detail, long timeStamp, String developerMessage) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}
