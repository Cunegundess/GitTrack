package com.cunegundess.GitTrack.client;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    public List<Map<String, Object>> getUserEvents(String username) {
        String url = githubApiUrl + "/users/" + username + "/events";
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            createHeaders(), 
            new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
        return response.getBody();
    }
}
