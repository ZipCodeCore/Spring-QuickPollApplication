package io.zipcoder.tc_spring_poll_application.error;

import java.util.List;
import java.util.Map;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.error
 * author: https://github.com/vvmk
 * date: 4/7/18
 */
public class ErrorDetail {
    String title;
    int status;
    String detail;
    long timestamp;
    String developerMessage;

    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }

    Map<String, List<ValidationError>> errors;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
