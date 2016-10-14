package com.alorma.gitskarios.data;

import com.alorma.gitskarios.domain.MessageRequest;
import com.alorma.gitskarios.domain.response.MessageResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageService {
    @POST("/send")
    Call<MessageResponse> sendMessage(@Body MessageRequest messageRequest);
}