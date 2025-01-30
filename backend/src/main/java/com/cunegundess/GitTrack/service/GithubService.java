package com.cunegundess.GitTrack.service;

import org.springframework.stereotype.Service;

import com.cunegundess.GitTrack.client.GithubClient;

@Service
public class GithubService {
    private final GithubClient githubClient;

    public GithubService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public String getUserEvents(String username) {
        return githubClient.getUserEvents(username);
    }

}
