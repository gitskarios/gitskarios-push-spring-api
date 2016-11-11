package com.alorma.gitskarios.data;

import com.alorma.gitskarios.domain.IssueEvent;
import com.alorma.gitskarios.domain.MessageRequest;
import com.alorma.gitskarios.domain.MessagesRepository;
import com.alorma.gitskarios.domain.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;

public class FirebaseMessageNotificationRepository implements MessagesRepository {

    private MessageService messageService;

    public FirebaseMessageNotificationRepository(MessageService  messageService) {

        this.messageService = messageService;
    }

    @Override
    public ResponseEntity sendMessage(IssueEvent event) throws Exception {
        if (event.getIssue() == null || event.getRepository() == null) {
            throw new UnsupportedOperationException("Event not handled");
        } else {
            String issueRepositoryName = getIssueName(event);
            if (issueRepositoryName == null) {
                throw new UnsupportedOperationException("Event not handled");
            } else {
                issueRepositoryName = issueRepositoryName.replace("/", "-");
                return sendMessage(issueRepositoryName, event);
            }
        }
    }

    private ResponseEntity sendMessage(String issueRepositoryName, IssueEvent event) throws Exception {
        MessageRequest request = new MessageRequest();
        String topic = "/topics/" + issueRepositoryName;
        request.setTo(topic);
        request.setData(event);
        Call<MessageResponse> call = messageService.sendMessage(request);
        Response<MessageResponse> response = call.execute();

        if (response.isSuccessful()) {
            MessageResponse messageResponse = response.body();
            messageResponse.setCode(response.code());
            messageResponse.setTopic(topic);
            return new ResponseEntity(messageResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(response.errorBody().string(), HttpStatus.BAD_REQUEST);
        }
    }

    private String getIssueName(IssueEvent event) {
        return event.getRepository().getFull_name();
    }
}
