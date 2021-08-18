package io.zipcoder.tc_spring_poll_application.error;

public class ValidationError {

    String code;
    String messege;

    public ValidationError() {
    }

    public ValidationError(String code, String messege) {
        this.code = code;
        this.messege = messege;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }
}
