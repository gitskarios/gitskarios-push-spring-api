package com.alorma.gitskarios.domain;

public class MessageRequest {
    private String to;
    private IssueEvent event;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public IssueEvent getEvent() {
        return event;
    }

    public void setEvent(IssueEvent event) {
        this.event = event;
    }
}
