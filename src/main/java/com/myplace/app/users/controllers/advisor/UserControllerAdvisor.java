package com.myplace.app.users.controllers.advisor;

import com.myplace.app.users.services.UserAlreadyLikesThisPlaceException;
import com.myplace.app.users.services.UserOrPlaceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserControllerAdvisor {
    @ExceptionHandler(value = {UserOrPlaceException.class})
    public ResponseEntity<Object> handleNotFoundException(UserOrPlaceException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {UserAlreadyLikesThisPlaceException.class})
    public ResponseEntity<Object> handleUserAlreadyLiked(UserAlreadyLikesThisPlaceException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
