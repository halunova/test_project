package ru.startandroid.testproject.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Виктория on 02.03.2018.
 */

public class Permission {
    @SerializedName("admin")
    private boolean admin;

    public boolean getAdmin() {
        return admin;
    }
}
