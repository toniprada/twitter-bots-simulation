package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by toni on 20/05/14.
 */
public class ExpUrl implements Serializable {

    @Expose
    @SerializedName("expanded_url") private String expandedUrl;

    public ExpUrl(String url) {
        this.expandedUrl = url;
    }
}
