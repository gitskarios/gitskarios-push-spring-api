package com.alorma.gitskarios.domain;

import org.springframework.http.ResponseEntity;

public interface MessagesRepository {
    ResponseEntity sendMessage(IssueEvent event) throws Exception;
}
