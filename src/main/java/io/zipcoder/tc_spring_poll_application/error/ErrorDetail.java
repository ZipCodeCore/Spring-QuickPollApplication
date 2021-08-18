package io.zipcoder.tc_spring_poll_application.error;

import java.util.List;
import java.util.Map;

public class ErrorDetail {

    String title;
    int status;
    String detail;
    Long timeStamp;
    String developerMessege;

    Map<String, List<ValidationError>> errors;


    public ErrorDetail() {
    }

    public ErrorDetail(String title, int status, String detail, Long timeStamp, String developerMessege) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessege = developerMessege;
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

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeveloperMessege() {
        return developerMessege;
    }

    public void setDeveloperMessege(String developerMessege) {
        this.developerMessege = developerMessege;
    }


    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }
}
