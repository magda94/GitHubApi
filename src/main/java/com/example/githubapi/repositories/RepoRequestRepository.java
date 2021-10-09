package com.example.githubapi.repositories;

import com.example.githubapi.entity.RepoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRequestRepository extends JpaRepository<RepoRequest, Long> { }
