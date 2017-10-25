package com.example;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 24.10.2017.
 */

public class Result {
    @SerializedName("s")
    private Integer sum;
    @SerializedName("e")
    private String error;

    public Result() {
    }

    public int getSum() {
        return this.sum.intValue();
    }

    public void setSum(int pSum) {
        this.sum = Integer.valueOf(pSum);
    }

    public String getError() {
        return this.error;
    }

    public void setError(String pError) {
        this.error = pError;
    }
}

