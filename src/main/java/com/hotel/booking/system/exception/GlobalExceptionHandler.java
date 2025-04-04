package com.hotel.booking.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(InvalidBookingDateEx.class)
    public ResponseEntity<ErrorResponse> invalidBookingDateExHandler(InvalidBookingDateEx invalidBookingDateEx,
                                                                      ServletWebRequest servletWebRequest     ) {
       ErrorResponse errorResponse = new ErrorResponse(
               LocalDateTime.now(),
               invalidBookingDateEx.getMessage(),
               servletWebRequest.getDescription(false),
               "BAD_REQUEST"
       );
       return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RoomNotFoundEx.class)
    public ResponseEntity<ErrorResponse> roomNotFoundExHandler(RoomNotFoundEx roomNotFoundEx,
                                                        ServletWebRequest servletWebRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                roomNotFoundEx.getMessage(),
                servletWebRequest.getDescription(false),
                "NOT_FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundExHandler(ResourceNotFoundEx resourceNotFoundEx,
                                                            ServletWebRequest servletWebRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                resourceNotFoundEx.getMessage(),
                servletWebRequest.getDescription(false),
                "NOT_FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotAvailableRoomEx.class)
    public ResponseEntity<ErrorResponse> notAvailableRoomExHandler(NotAvailableRoomEx notAvailableRoomEx,ServletWebRequest servletWebRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                notAvailableRoomEx.getMessage(),
                servletWebRequest.getDescription(false),
                "NOT_FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex, ServletWebRequest servletWebRequest) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                servletWebRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationEx(MethodArgumentNotValidException methodArgumentNotValidException,
                                                            ServletWebRequest servletWebRequest) {
        String errorMessage = methodArgumentNotValidException.getBindingResult()
                .getFieldErrors().stream()
                .map(error -> error.getField() + "" + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                errorMessage,
                servletWebRequest.getDescription(false),
                "BAD_REQUEST"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
