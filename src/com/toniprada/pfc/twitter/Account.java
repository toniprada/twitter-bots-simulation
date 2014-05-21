package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;
import com.toniprada.pfc.util.SourcesList;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by toni on 19/05/14.
 */
public class Account implements Serializable {

    private static final String LOREM = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor" +
            " incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco" +
            " laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in" +
            " voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat" +
            " non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";


    private static final long serialVersionUID = 1L;
    private static final double FOLLOWNESS_THRESHOLD = 0.9;
    private static final double TWEETNESS_THRESHOLD = 0.7;

    private double followness;
    private double tweetness;
    private Object[] sources;

    @Expose private Profile profile;
    @Expose private ArrayList<Relation> followers;
    @Expose private ArrayList<Relation> friends;
    @Expose private ArrayList<Tweet> tweets;

    public Account() {
        this.sources = SourcesList.getRandomSources();
        this.followness = Math.random();
        this.tweetness = Math.random();
        // exposed data
        this.profile = new Profile();
        this.followers = new ArrayList<Relation>();
        this.friends = new ArrayList<Relation>();
        this.tweets = new ArrayList<Tweet>();
    }

    public boolean shouldFollow() {
        return Math.random()*followness > FOLLOWNESS_THRESHOLD;
    }

    public boolean shouldTweet() {
        return Math.random()*tweetness > TWEETNESS_THRESHOLD;
    }

    /* Getters and setters */

    public Profile getProfile() {
        return profile;
    }

    public void addFollower(Relation follower) {
        this.followers.add(follower);
        this.profile.incrementFollowers();
    }

    public void addFriend(Relation friend) {
        this.friends.add(friend);
        this.profile.incrementFriends();
    }

    public void tweet() {
        String source = (String) sources[(int) (Math.random() * (sources.length - 1))];
        String text = LOREM.substring(0, ((int)(Math.random()*140)));
        tweets.add(new Tweet(text, source));
        this.profile.incrementStatuses();
    }
}
