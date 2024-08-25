package pe.edu.upc.eventra.payment_service.shared.exception;

import feign.FeignException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return ResponseEntity.status(e.status()).body(e.contentUTF8());
    }

}

