package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by toni on 20/05/14.
 */
public class UrlEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose private ArrayList<ExpUrl> urls;

    public UrlEntity(String url) {
        urls = new ArrayList<ExpUrl>();
        urls.add(new ExpUrl(url));
    }
}
