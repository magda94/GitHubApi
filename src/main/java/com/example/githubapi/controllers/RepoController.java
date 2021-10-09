package com.example.githubapi.controllers;

import com.example.githubapi.clients.ReposClient;
import com.example.githubapi.entity.Repo;
import com.example.githubapi.entity.RepoRequest;
import com.example.githubapi.repositories.RepoRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class RepoController {

    private final ReposClient reposClient;
    private final RepoRequestRepository repoRequestRepository;

    @Autowired
    public RepoController(ReposClient reposClient, RepoRequestRepository repoRequestRepository) {
        this.reposClient = reposClient;
        this.repoRequestRepository = repoRequestRepository;
    }

    @GetMapping("repositories/{owner}/{repoName}")
    public ResponseEntity<Repo> getRepo(@PathVariable String owner, @PathVariable String repoName) {
        addRequestToDatabase(owner, repoName);
        return ResponseEntity.ok(reposClient.getRepo(owner, repoName));
    }

    @GetMapping("repositories/{owner}")
    public ResponseEntity<List<Repo>> getRepos(@PathVariable String owner) {
        return ResponseEntity.ok(reposClient.getReposByOwner(owner));
    }

    private void addRequestToDatabase(String owner, String repoName) {
        String endpoint = String.format("%s/%s", owner, repoName);
        String date = String.format("%Tc", new Date());
        repoRequestRepository.save(new RepoRequest(endpoint, date));
    }
}
