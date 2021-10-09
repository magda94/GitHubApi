package com.example.githubapi.repositories;

import com.example.githubapi.entity.RepoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepoRequestRepositoryTest {

    @Autowired
    RepoRequestRepository repository;

    @Test
    public void saveEntityShouldHaveNotNullIdWhenSavedToRepository() {
        RepoRequest request = new RepoRequest("owner/repo", new Date().toString());

        repository.save(request);
        assertNotNull(request.getId());
    }

    @Test
    public void findAllShouldReturnEmptyListIfThereIsNoRequests() {
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    public void findAllShouldReturnAllRequest() {
        repository.save(new RepoRequest("owner/repo", new Date().toString()));
        repository.save(new RepoRequest("owner2/repo2", new Date().toString()));

        assertThat(repository.findAll())
                .extracting("endpoint")
                .containsExactly("owner/repo", "owner2/repo2");
    }
}
