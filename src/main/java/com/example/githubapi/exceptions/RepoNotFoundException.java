package com.example.githubapi.exceptions;

public class RepoNotFoundException  extends RuntimeException {
    public RepoNotFoundException(String message) {
        super(message);
    }
}
