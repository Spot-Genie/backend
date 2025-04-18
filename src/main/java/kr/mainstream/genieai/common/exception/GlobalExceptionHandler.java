package kr.mainstream.genieai.common.exception;

import io.sentry.Sentry;
import kr.mainstream.genieai.common.container.ErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientAbortException.class)
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void handleClientAbortException(ClientAbortException e) {
        log.info("ClientAbortException is occurred. {}", e.toString());
    }

    @ExceptionHandler(ValidationIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleValidationIllegalArgumentException(ValidationIllegalArgumentException e) {
        log.info(e.getErrors().toString(), e);
        return ErrorsResponse.create(e.getErrors());
    }

    @ExceptionHandler(SimpleMessageIllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleSimpleMessageIllegalArgumentException(SimpleMessageIllegalArgumentException e) {
        log.info(e.toString(), e);
        return ErrorsResponse.create(e.getMessage(), e.getField());
    }

    @ExceptionHandler(SimpleMessageIllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleSimpleMessageIllegalStateException(SimpleMessageIllegalStateException e) {
        log.info(e.toString(), e);
        return ErrorsResponse.create(e.getMessage(), e.getField());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponse handleIOException(IOException e) {
        log.error(e.toString(), e);
        Sentry.captureException(e);
        return ErrorsResponse.create("치명적인 에러가 발생하였습니다. 관리자에게 문의하세요", null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorsResponse handleNotFoundException(NotFoundException e) {
        log.info(e.toString(), e);
        return ErrorsResponse.create(e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsResponse handleUnhandledException(Exception e) {
        log.error(e.toString(), e);
        Sentry.captureException(e);
        return ErrorsResponse.create("치명적인 에러가 발생하였습니다. 관리자에게 문의하세요.", null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorsResponse handleNoHandlerFoundException(Exception e) {
        log.error(e.toString(), e);
        return ErrorsResponse.create("URL not found", null);
    }
}
