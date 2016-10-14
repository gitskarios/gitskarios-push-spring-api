package com.alorma.gitskarios.view;

import com.alorma.gitskarios.data.IssueEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessagesController {

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> handleMessage(IssueEvent event) {
        return new ResponseEntity<>("Received", HttpStatus.OK);
    }

}
