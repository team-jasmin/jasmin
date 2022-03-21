package io.github.jasmin.exception.handler;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import io.github.jasmin.exception.NotFoundException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> errorResponse(NotFoundException e, HttpServletRequest request) {
        return ResponseEntity.badRequest()
            .body(ErrorResponse.of(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(),
                e.getMessage(), request.getRequestURI(), LocalDateTime.now()));
    }

    @Value(staticConstructor = "of")
    private static class ErrorResponse {
        int status;
        String message;
        String error;
        String path;
        LocalDateTime timestamp;
    }

}
