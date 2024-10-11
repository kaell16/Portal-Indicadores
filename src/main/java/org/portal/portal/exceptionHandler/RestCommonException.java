package org.portal.portal.exceptionHandler;

import org.portal.portal.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestCommonException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionNotFound.class)
    private ResponseEntity<RestErrorMessage> notFoundHandler(ExceptionNotFound exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ExceptionExcelEmpty.class)
    private ResponseEntity<RestErrorMessage> excelEmptyHandler(ExceptionExcelEmpty exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(ExceptionIndicadorNotFound.class)
    private ResponseEntity<RestErrorMessage> indicadorNotFoundHandler(ExceptionIndicadorNotFound exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(ExceptionDataEmpty.class)
    private ResponseEntity<RestErrorMessage> dataEmptyHandler(ExceptionDataEmpty exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(ExceptionRecordsNotPersisted.class)
    private ResponseEntity<RestErrorMessage> recordsNotPersistedHandler(ExceptionRecordsNotPersisted exception) {
        RestErrorMessage response = new RestErrorMessage(HttpStatus.EXPECTATION_FAILED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }

}
