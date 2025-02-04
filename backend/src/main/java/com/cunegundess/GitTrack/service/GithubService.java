package com.cunegundess.GitTrack.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cunegundess.GitTrack.client.GithubClient;

@Service
public class GithubService {
    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<Map<String, Object>> getUserEvents(String username) {
        return githubClient.getUserEvents(username);
    }

    public Set<String> getUserRepos(String username) {
        List<Map<String, Object>> events = githubClient.getUserEvents(username);
        Set<String> repoNames = new HashSet<String>();

        try {
            for (Map<String, Object> event : events) {
                if (event.containsKey("repo")) {
                    Map<String, Object> repo = (Map<String, Object>) event.get("repo");
                    if (repo.containsKey("name")) {
                        repoNames.add(repo.get("name").toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error getUserRepos " + e.getMessage());
        }

        return repoNames;
    }

}
