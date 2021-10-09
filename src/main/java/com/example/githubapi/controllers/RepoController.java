package com.example.githubapi.controllers;

import com.example.githubapi.clients.ReposClient;
import com.example.githubapi.entity.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepoController {

    private final ReposClient reposClient;

    @Autowired
    public RepoController(ReposClient reposClient) {
        this.reposClient = reposClient;
    }

    @GetMapping("repositories/{owner}/{repoName}")
    public ResponseEntity<Repo> getRepo(@PathVariable String owner, @PathVariable String repoName) {
        return ResponseEntity.ok(reposClient.getRepo(owner, repoName));
    }

    @GetMapping("repositories/{owner}")
    public ResponseEntity<List<Repo>> getRepos(@PathVariable String owner) {
        return ResponseEntity.ok(reposClient.getReposByOwner(owner));
    }
}
