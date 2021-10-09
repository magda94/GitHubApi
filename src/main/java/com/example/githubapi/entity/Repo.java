package com.example.githubapi.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Repo {
    @JsonAlias({"full_name"})
    private String fullName;

    @JsonAlias({"description"})
    private String description;

    @JsonAlias({"clone_url"})
    private String cloneUrl;

    @JsonAlias({"stargazers_count"})
    private int stars;

    @JsonAlias({"created_at"})
    private LocalDateTime createdAt;
}
