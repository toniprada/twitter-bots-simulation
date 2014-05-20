package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by toni on 19/05/14.
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final double FOLLOWNESS_THRESHOLD = 0.9;

    private double followness;

    @Expose private Profile profile;
    @Expose private ArrayList<String> followers;
    @Expose private ArrayList<String> following;
    @Expose private ArrayList<String> tweets;

    public Account() {
        this.followness = Math.random();
        // exposed data
        this.profile = new Profile();
        this.followers = new ArrayList<String>();
        this.following = new ArrayList<String>();
        this.tweets = new ArrayList<String>();
    }

    public boolean shouldMakeFollow() {
        return Math.random()*followness > FOLLOWNESS_THRESHOLD;
    }

    /* Getters and setters */

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public ArrayList<String> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<String> tweets) {
        this.tweets = tweets;
    }

}
