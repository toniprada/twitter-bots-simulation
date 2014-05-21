package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by toni on 20/05/14.
 */
public class Entities implements Serializable{

    private static final long serialVersionUID = 1L;

    @Expose
    private UrlEntity url;

    public Entities(String urlString) {
        url = new UrlEntity(urlString);
    }

}
