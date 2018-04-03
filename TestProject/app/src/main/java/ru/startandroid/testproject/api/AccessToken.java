package ru.startandroid.testproject.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Виктория on 18.02.2018.
 */

public class AccessToken {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

}
