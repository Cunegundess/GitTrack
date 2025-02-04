package com.cunegundess.GitTrack.service;

public enum GithubEventTypes {
    COMMIT_COMMENT_EVENT("CommitCommentEvent"),
    CREATE_EVENT("CreateEvent"),
    DELETE_EVENT("DeleteEvent"),
    FORK_EVENT("ForkEvent"),
    GOLLUM_EVENT("GollumEvent"),
    ISSUE_COMMENT_EVENT("IssueCommentEvent"),
    ISSUES_EVENT("IssuesEvent"),
    MEMBER_EVENT("MemberEvent"),
    PUBLIC_EVENT("PublicEvent"),
    PULL_REQUEST_EVENT("PullRequestEvent"),
    PULL_REQUEST_REVIEW_EVENT("PullRequestReviewEvent"),
    PULL_REQUEST_REVIEW_COMMENT_EVENT("PullRequestReviewCommentEvent"),
    PULL_REQUEST_REVIEW_THREAD_EVENT("PullRequestReviewThreadEvent"),
    PUSH_EVENT("PushEvent"),
    RELEASE_EVENT("ReleaseEvent"),
    SPONSORSHIP_EVENT("SponsorshipEvent"),
    WATCH_EVENT("WatchEvent"),;

    private final String eventType;

    GithubEventTypes(String eventType) {this.eventType = eventType;}

    public String getEventType() {
        return eventType;
    }

    public static GithubEventTypes fromString(String eventType) {
       for (GithubEventTypes type : GithubEventTypes.values()) {
           if (type.getEventType().equalsIgnoreCase(eventType)) { return type; }
       }
       throw new IllegalArgumentException("Invalid event type: " + eventType);
    }
}
