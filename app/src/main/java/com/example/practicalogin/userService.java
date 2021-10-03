package com.example.practicalogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface userService {

    @POST("login")
    Call<loginResponse> userlogin(@Body loginRequest loginRequest);

}
