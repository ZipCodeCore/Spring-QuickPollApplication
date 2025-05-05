package io.zipcoder.tc_spring_poll_application.exception;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import io.zipcoder.tc_spring_poll_application.error.*;
import java.util.*;
import java.io.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.MessageSource;
import org.springframework.validation.*;
import org.springframework.beans.factory.annotation.Autowired;

@ControllerAdvice
public class RestExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail detail = new ErrorDetail();
        detail.setTimeStamp(new Date().getTime());
        detail.setDetail(rnfe.getMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        rnfe.printStackTrace(pw);
        detail.setDeveloperMessage(sw.toString());

        detail.setTitle("Resource Not Found Exception");
        detail.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorDetail>(detail, HttpStatus.NOT_FOUND);}


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>
    handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setTitle("Resource Validation Failed");
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setDetail(manve.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        manve.printStackTrace(pw);
        errorDetail.setDeveloperMessage(sw.toString());

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
      return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
