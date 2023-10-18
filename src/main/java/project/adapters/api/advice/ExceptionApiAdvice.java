package project.adapters.api.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.domain.exceptions.BadRequestException;
import project.domain.utils.MessageUtils;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionApiAdvice extends ResponseEntityExceptionHandler {

    private final MessageUtils messageUtils;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleNotFoundException(BadRequestException ex) {
        return new ErrorResponse(messageUtils.findMessage(ex.getError().getCode()));
    }
}
