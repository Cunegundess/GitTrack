package com.cunegundess.GitTrack.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {
    private final RestTemplate restTemplate;

    @Value("${github.api.url}")
    private String githubApiUrl;

    public GithubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpEntity<String> createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github.v3+json");
        return new HttpEntity<>(headers);
    }

    // public String getUserRepos(String username) {
    //     String url = githubApiUrl + "/users/" + username + "/repos";
    //     ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createHeaders(), String.class);
    //     return response.getBody();
    // }

    public String getUserEvents(String username) {
        String url = githubApiUrl + "/users/" + username + "/events";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createHeaders(), String.class);
        return response.getBody();
    }

    // public String getCommits(String owner, String repo) {
    //     String url = githubApiUrl + "/repos/" + owner + "/" + repo + "/commits";
    //     ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, createHeaders(), String.class);
    //     return response.getBody();
    // }
}
