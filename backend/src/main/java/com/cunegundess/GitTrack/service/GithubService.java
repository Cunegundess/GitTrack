package com.cunegundess.GitTrack.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, String> getUserRepos(String username) {
        List<Map<String, Object>> events = githubClient.getUserEvents(username);
        Map<String, String> userRepos = new HashMap<>();

        for (Map<String, Object> event : events) {
            try {
                if (event.containsKey("repo")) {
                    Map<String, Object> repo = (Map<String, Object>) event.get("repo");

                    if (repo.containsKey("name") && repo.containsKey("url")) {
                        userRepos.put(repo.get("name").toString(), repo.get("url").toString());
                    }
                }
            } catch (Exception e) {
                System.err.println("Error processing event in getUserRepos: " + e.getMessage());
            }
        }

        return userRepos;
    }

    public Map<String, Object> getRepoCommits(String username) {
        Map<String, String> repoNames = getUserRepos(username);
        List<Map<String, Object>> events = githubClient.getUserEvents(username);
        Map<String, Object> repoCommits = new HashMap<>();

        for (Map<String, Object> event : events) {
            try {
                if (event.get("type").equals(GithubEventTypes.PUSH_EVENT.getEventType())) {
                    Map<String, Object> repo = (Map<String, Object>) event.get("repo");
                    String repoName = repo.get("name").toString();

                    if (repoNames.containsKey(repoName) && event.containsKey("payload")) {
                        Map<String, Object> payload = (Map<String, Object>) event.get("payload");
                        
                        if (payload.containsKey("commits") && payload.get("commits") instanceof List) {
                            List<Map<String, Object>> commits = (List<Map<String, Object>>) payload.get("commits");
                            repoCommits.putIfAbsent(repoName, new ArrayList<>());
                            ((List<Object>) repoCommits.get(repoName)).addAll(commits);
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Error processing event: " + e.getMessage());
            }
        }

        return repoCommits;
    }

}
