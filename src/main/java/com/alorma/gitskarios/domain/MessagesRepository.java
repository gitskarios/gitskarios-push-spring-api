package com.alorma.gitskarios.domain;

import com.alorma.gitskarios.domain.response.MessageResponse;

public interface MessagesRepository {
    MessageResponse sendMessage(IssueEvent event) throws Exception;
}
