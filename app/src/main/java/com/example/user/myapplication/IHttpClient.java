package com.example.user.myapplication;

/**
 * Created by User on 25.10.2017.
 */

public interface IHttpClient {
    void request(String url, HttpClient.ResponseListener responseListener);
}
