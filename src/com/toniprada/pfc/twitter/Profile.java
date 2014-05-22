package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.toniprada.pfc.util.NameGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by toni on 19/05/14.
 */
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose private String id;
    @Expose private String name;
    @Expose @SerializedName("screen_name") private String screenName;
    @Expose private String description;
    @Expose private Entities entities;
    @Expose @SerializedName("followers_count") private int followersCount;
    @Expose @SerializedName("friends_count") private int friendsCount;
    @Expose @SerializedName("created_at") private String createdAt;
    @Expose @SerializedName("statuses_count") private int statusesCount;


    public Profile() {
        chooseId();
        chooseNames();
        chooseBio();
        chooseUrl();
        this.followersCount = 0;
        this.friendsCount = 0;
        chooseCreatedAt();
        this.statusesCount = 0;
    }

    private void chooseId() {
        this.id = "" + ((int)(Math.random()*1000000000));
    }

    private void chooseNames() {
        this.name = NameGenerator.generate();
        this.screenName = name.replaceAll("\\W", "_").toLowerCase();
    }

    private void chooseBio() {
        String lorem = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt" +
                " ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco" +
                " laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in" +
                " voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat" +
                " non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        this.description = lorem.substring(0, ((int)(Math.random()*160)));
    }

    private void chooseUrl() {
        double dice = Math.random();
        // Empty url
        if (dice > 0.3 &&  dice < 0.6) {
            // Domain alike to the name
            String url = "http://" + screenName.substring(0,  Math.max(4,(int)(Math.random()*screenName.length() - 1))) + ".com";
            this.entities = new Entities(url);
        } else {
            // popular domain
            String url = "http://instagram.com/"  + screenName;
            this.entities = new Entities(url);
        }
    }

    private void chooseCreatedAt() {
        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2007, 2013);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));


        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        SimpleDateFormat format1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss XX Y"); //Sat Mar 17 20:12:08 +0000 2007"
        this.createdAt = format1.format(gc.getTime());

    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void incrementFriends() {
        this.friendsCount++;
    }

    public void incrementFollowers() {
        this.followersCount++;
    }

    public void incrementStatuses() {
        this.statusesCount++;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }
}
