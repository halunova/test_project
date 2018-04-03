package ru.startandroid.testproject.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Виктория on 02.03.2018.
 */

public class Owner {
    @SerializedName("login")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @SerializedName("avatar_url")
    private String avatarURL;

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
