package io.zipcoder.tc_spring_poll_application.error;

/**
 * project: spring-demo
 * package: io.zipcoder.tc_spring_poll_application.error
 * author: https://github.com/vvmk
 * date: 4/7/18
 */
public class ValidationError {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String code;
    String message;
}
