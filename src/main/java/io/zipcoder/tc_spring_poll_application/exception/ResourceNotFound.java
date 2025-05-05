package io.zipcoder.tc_spring_poll_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{



    public ResourceNotFound(){

    }

    public ResourceNotFound(String message){
        super(message);
    }

    public ResourceNotFound (String message, Throwable cause){
        super(message, cause);
    }
}
