package com.example.githubapi.clients;


import com.example.githubapi.entity.Repo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "repoClient", url = "https://api.github.com")
public interface ReposClient {

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repoName}")
    Repo getRepo(@PathVariable String owner, @PathVariable String repoName);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{owner}/repos")
    List<Repo> getReposByOwner(@PathVariable String owner);
}
