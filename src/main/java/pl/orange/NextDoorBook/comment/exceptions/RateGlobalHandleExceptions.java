package pl.orange.NextDoorBook.comment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RateGlobalHandleExceptions {
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RateIllegalArgumentException.class)
    String rateIllegalArgumentException(RateIllegalArgumentException exception){
        return exception.getMessage();
    }
}
