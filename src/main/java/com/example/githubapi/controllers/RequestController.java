package com.example.githubapi.controllers;

import com.example.githubapi.entity.RepoRequest;
import com.example.githubapi.repositories.RepoRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController {

    private final RepoRequestRepository repository;

    @Autowired
    public RequestController(RepoRequestRepository repository) {
        this.repository = repository;
    }

    @GetMapping("requests")
    public ResponseEntity<List<RepoRequest>> getRequests() {
        return ResponseEntity.ok(repository.findAll());
    }
}
