package io.zipcoder.tc_spring_poll_application.exception;

import dtos.error.ErrorDetail;
import dtos.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @Autowired
    MessageSource messageSource;


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>
    handleValidationError( MethodArgumentNotValidException manve,
                           HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDetail("Unable to locate resource, resource does not exist");
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDeveloperMessage(manve.getMessage());
        errorDetail.setTitle("Cannot locate resource");

        List<FieldError> fieldErrors =  manve.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors) {

            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
            if(validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
