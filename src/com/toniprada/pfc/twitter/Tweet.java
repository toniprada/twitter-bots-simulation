package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;

/**
 * Created by toni on 20/05/14.
 */
public class Tweet {

    @Expose private String text;
    @Expose private String source;
    @Expose private TweetEntities entities;

    public Tweet(String text, String source) {
        this.text = text;
        this.source = source;
        this.entities = new TweetEntities("http://instagram.com");
    }

}

