package com.example.githubapi.errorhandlers;

import com.example.githubapi.exceptions.RepoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RepoNotFoundExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = RepoNotFoundException.class)
    public ResponseEntity < ? > handleException(RepoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}