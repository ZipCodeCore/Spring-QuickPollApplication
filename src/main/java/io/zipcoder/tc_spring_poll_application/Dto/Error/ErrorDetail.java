package io.zipcoder.tc_spring_poll_application.Dto.Error;

import java.util.List;
import java.util.Map;

public class ErrorDetail {

    String title;
    int status;
    String detail;
    long timeStamp;
    String developerMessage;
    Map<String, List<ValidationError>> errors;

    public ErrorDetail(){
        this.detail = detail;
        this.developerMessage = developerMessage;
        this.status = status;
        this.title = title;
        this.timeStamp = timeStamp;
        this.errors = errors;
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
