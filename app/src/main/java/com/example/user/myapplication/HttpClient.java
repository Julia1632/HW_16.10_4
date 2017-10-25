package com.example.user.myapplication;

import android.support.annotation.VisibleForTesting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 25.10.2017.
 */

public class HttpClient implements IHttpClient {
    private HttpURLConnection con;

    @Override
    public void request(String url, ResponseListener responseListener) {

        try{
            final InputStream is=openStream(url);
            responseListener.onResponse(is);
            con.disconnect();
        }
        catch (final Throwable t){
            responseListener.onError(t);
        }
        finally {
            if (con!=null){
                con.disconnect();
            }
        }
    }
    @VisibleForTesting
    InputStream openStream(final String url) throws IOException {
        con=(HttpURLConnection)(new URL(url)).openConnection();
        return con.getInputStream();
    }
    public interface ResponseListener{
        void onResponse(InputStream inputStream) throws Exception;
        void onError(Throwable throwable);
    }
}
