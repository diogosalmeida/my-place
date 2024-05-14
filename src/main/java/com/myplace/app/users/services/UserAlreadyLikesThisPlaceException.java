package com.myplace.app.users.services;

public class UserAlreadyLikesThisPlaceException extends RuntimeException {

        public UserAlreadyLikesThisPlaceException(final String message) {
            super(message);
        }
}
