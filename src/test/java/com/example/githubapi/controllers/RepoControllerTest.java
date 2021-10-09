package com.example.githubapi.controllers;

import com.example.githubapi.exceptions.RepoNotFoundException;
import com.example.githubapi.repositories.RepoRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RepoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepoRequestRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void getRepoShouldReturnNotFoundCodeWhenRepoDoesNotExist() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/repositories/a/b")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cannot find repository."))
                .andReturn();

        assertThat(result.getResolvedException()).isInstanceOf(RepoNotFoundException.class);
    }

    @Test
    public void getRepoShouldReturnRepoDataWhenRepoExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/repositories/magda94/GitHubApi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fullName").value("magda94/GitHubApi"))
                .andExpect(jsonPath("$.cloneUrl").value("https://github.com/magda94/GitHubApi.git"))
                .andExpect(jsonPath("$.stars").value(0))
                .andExpect(jsonPath("$.createdAt").value("2021-10-09T08:04:16"));

        assertThat(repository.findAll())
                .isNotEmpty()
                .extracting("endpoint")
                .containsExactly("magda94/GitHubApi");
    }
}