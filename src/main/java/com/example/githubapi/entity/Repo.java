package com.example.githubapi.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Repo {
    @JsonAlias({"full_name"})
    String fullName;

    @JsonAlias({"description"})
    String description;

    @JsonAlias({"clone_url"})
    String cloneUrl;

    @JsonAlias({"stargazers_count"})
    int stars;

    @JsonAlias({"created_at"})
    LocalDateTime createdAt;
}
