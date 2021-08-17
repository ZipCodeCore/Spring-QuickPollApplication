package io.zipcoder.tc_spring_poll_application.exception;

import dtos.error.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail eDetail = new ErrorDetail();
        eDetail.setTimeStamp(new Date().getTime());
        eDetail.setDetail("Unable to locate resource, resource does not exist");
        eDetail.setStatus(HttpStatus.NOT_FOUND.value());
        eDetail.setDeveloperMessage(rnfe.getMessage());
        eDetail.setTitle("Cannot locate resource");

        return new ResponseEntity<>(eDetail, null, HttpStatus.NOT_FOUND);
    }

}
