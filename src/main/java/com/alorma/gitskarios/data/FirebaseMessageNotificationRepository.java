package com.alorma.gitskarios.data;

import com.alorma.gitskarios.domain.IssueEvent;
import com.alorma.gitskarios.domain.MessageRequest;
import com.alorma.gitskarios.domain.MessagesRepository;
import com.alorma.gitskarios.domain.response.MessageResponse;
import retrofit2.Call;
import retrofit2.Response;

public class FirebaseMessageNotificationRepository implements MessagesRepository {

    private MessageService messageService;

    public FirebaseMessageNotificationRepository(MessageService  messageService) {

        this.messageService = messageService;
    }

    @Override
    public MessageResponse sendMessage(IssueEvent event) throws Exception {
        if (event.getIssue() == null || event.getRepository() == null) {
            throw new UnsupportedOperationException("Event not handled");
        } else {
            String issueRepositoryName = getIssueName(event);
            if (issueRepositoryName == null) {
                throw new UnsupportedOperationException("Event not handled");
            } else {
                return sendMessage(issueRepositoryName, event);
            }
        }
    }

    private MessageResponse sendMessage(String issueRepositoryName, IssueEvent event) throws Exception {
        MessageRequest request = new MessageRequest();
        request.setTo("/topics/" + issueRepositoryName);
        request.setEvent(event);
        Call<MessageResponse> call = messageService.sendMessage(request);
        Response<MessageResponse> response = call.execute();

        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.errorBody().string());
        }
    }

    private String getIssueName(IssueEvent event) {
        return event.getRepository().getFull_name();
    }
}
