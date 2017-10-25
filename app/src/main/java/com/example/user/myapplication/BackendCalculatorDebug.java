package com.example.user.myapplication;

import com.example.CalculatorMethods;
import com.example.Result;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

public class BackendCalculatorDebug implements ICalculator {

    String base_url=Constants.BASE_DEBUG_URL;
    @Override
    public String plus(int a, int b) {
        final String url = new CalculatorMethods(base_url).calculateSum(a,b);
        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);
        return String.valueOf(listener.getResult().getSum());
    }

    private static class MyResponseListener implements HttpClient.ResponseListener {

        private Result result;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                result = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, Result.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {}
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        public Result getResult() {
            return result;
        }


    }

    @Override
    public String evaluate(String value){
        final String url = new CalculatorMethods(base_url).evaluate(value);
        final MyResponseListener listener = new MyResponseListener();
        new HttpClient().request(url, listener);
        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new UnsupportedOperationException(listener.getThrowable());
        }
        return String.valueOf(listener.getResult().getSum());
    }
}
