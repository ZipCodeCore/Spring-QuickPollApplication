package io.zipcoder.tc_spring_poll_application.dtos.error;


import java.util.List;
import java.util.Map;

public class ErrorDetail {

    private String title; // brief error condition - "Validation Failure" or "Internal Server Error"
    private int status;  // HTTP status code
    private String detail; // human-readable desc. of error
    private long timeStamp; // in milliseconds when it happened
    private String developerMessage; // deatiled info for a dev to understand

    private Map<String, List<ValidationError>> errors;

    public ErrorDetail() {
    }

    public ErrorDetail(String title, int status, String detail, long timeStamp, String developerMessage) {
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
