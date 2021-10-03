package com.example.practicalogin;

import com.google.gson.Gson;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {

    private static Retrofit getRetrofit(){


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();



        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://movilesjoaquin.000webhostapp.com/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static userService getUserService(){

        userService userService = getRetrofit().create(userService.class);

        return  userService;
    }
}
