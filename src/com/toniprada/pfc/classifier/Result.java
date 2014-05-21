package com.toniprada.pfc.classifier;

import com.google.gson.annotations.Expose;

/**
 * Created by toni on 19/05/14.
 */
public class Result {

    @Expose private boolean result;

    public Result() {}

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
