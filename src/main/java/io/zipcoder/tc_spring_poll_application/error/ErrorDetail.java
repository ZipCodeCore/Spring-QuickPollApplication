package io.zipcoder.tc_spring_poll_application.error;

import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private String title; // title of error condition
    private int status; // HTTP status code for current request
    private String detail; // short readable description of error
    private long timeStamp; // time in milliseconds when error occurred
    private String developerMessage; // detailed info as such exception class or trace
    private Map<String, List<ValidationError>> errors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
