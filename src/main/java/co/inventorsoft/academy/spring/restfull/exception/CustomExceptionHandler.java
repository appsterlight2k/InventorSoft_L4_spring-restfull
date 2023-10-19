package co.inventorsoft.academy.spring.restfull.exception;

import co.inventorsoft.academy.spring.restfull.dto.WebResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public WebResponse<Object> handelItemNotFound(ItemNotFoundException e) {
        return new WebResponse<>(null, e.getMessage(), false, 0);
    }
}
