package com.alorma.gitskarios.view;

import com.alorma.gitskarios.domain.IssueEvent;
import com.alorma.gitskarios.domain.MessagesRepository;
import com.alorma.gitskarios.domain.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessagesController {

    @Autowired
    @Qualifier("firebase")
    private MessagesRepository messagesRepository;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> handleMessage(@RequestBody IssueEvent event) throws Exception {
        MessageResponse messageResponse = messagesRepository.sendMessage(event);
        if (messageResponse.getError() != null) {
            return new ResponseEntity<>(messageResponse.getError(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(messageResponse.getMessage_id(), HttpStatus.OK);
    }

}
