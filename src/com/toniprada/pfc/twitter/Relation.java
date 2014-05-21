package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by toni on 20/05/14.
 */
public class Relation {

    @Expose private String id;
    @Expose private String name;
    @Expose @SerializedName("screen_name") private String screenName;


    public Relation(Account account) {
        this.id = account.getProfile().getId();
        this.name = account.getProfile().getName();
        this.screenName = account.getProfile().getScreenName();
    }

}
