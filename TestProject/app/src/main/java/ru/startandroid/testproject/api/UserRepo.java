package ru.startandroid.testproject.api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Виктория on 06.02.2018.
 */

public class UserRepo {
    @SerializedName("name")
    private String name;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("permissions")
    private Permission permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Permission getPermission() {
        return permission;
    }
}
