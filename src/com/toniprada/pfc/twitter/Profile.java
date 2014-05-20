package com.toniprada.pfc.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.toniprada.pfc.util.NameGenerator;

import java.io.Serializable;

/**
 * Created by toni on 19/05/14.
 */
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Expose private String id;
    @Expose private String name;
    @Expose @SerializedName("screen_name") private String screenName;
    @Expose private String location;
    @Expose private String description;
    @Expose private String url;
    @Expose @SerializedName("protected") private boolean isProtected;
    @Expose @SerializedName("followers_count") private int followersCount;
    @Expose @SerializedName("friends_count") private int friendsCount;
    @Expose @SerializedName("listed_count") private int listedCount;
    @Expose @SerializedName("created_at") private String createdAt;
    @Expose @SerializedName("favourites_count") private int favouritesCount;
    @Expose @SerializedName("statuses_count") private int statusesCount;
    @Expose @SerializedName("profile_use_background_image") private boolean profileUseBackgroundImage;
    @Expose @SerializedName("default_profile") private boolean defaultProfile;
    @Expose @SerializedName("default_profile_image") private boolean defaultProfileImage;


    public Profile() {
        this.id = "" + ((int)(Math.random()*1000000000));
        chooseNames();
        this.location = ""; // FIXME hay que hacerlo?
        this.description = ""; // TODO
        this.url = ""; // TODO
        // FIXME miro las entities para la url??
        this.followersCount = 0; // TODO
        this.friendsCount = 0; // TODO
        this.listedCount = 0; // FIXME hay que hacerlo?
        this.createdAt = ""; // FIXME hay que hacerlo?
        this.favouritesCount = 0; // FIXME hay que hacerlo?
        this.statusesCount = 0; // TODO
        this.profileUseBackgroundImage = false; // TODO
        this.defaultProfile = false; // TODO
        this.defaultProfileImage = false; // TODO
        // FIXME mirar si me he dejado alguna...
    }

    private void chooseNames() {
        this.name = NameGenerator.generate();
        this.screenName = name.replaceAll("\\W", "_").toLowerCase();
    }

    public String getName() {
        return name;
    }

}
