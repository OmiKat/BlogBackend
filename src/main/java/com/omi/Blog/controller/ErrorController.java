package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class ErrorController {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex){
        log.error("caught exception" , ex);
        ApiError errorMsg = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .build();

        return new ResponseEntity<>(errorMsg,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handelIllegalArgumentException(IllegalArgumentException ex){
        ApiError error = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handelIllegalStateException(IllegalStateException ex){
        ApiError error = ApiError.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

}
