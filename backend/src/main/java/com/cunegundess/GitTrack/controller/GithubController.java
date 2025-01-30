package com.cunegundess.GitTrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cunegundess.GitTrack.service.GithubService;

@RestController
@RequestMapping("/")
public class GithubController {
    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("{username}")
    public String getUserEvents(@PathVariable String username) {
        return githubService.getUserEvents(username);
    }

}
