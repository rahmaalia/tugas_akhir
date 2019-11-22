package com.rahma.antriyuk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rahma.antriyuk.Entity.EHistory;

import java.util.List;

public class Mhistory {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("bio")
    @Expose
    private Mhistory bio;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Mhistory getBio() {
        return bio;
    }

    public void setBio(Mhistory bio) {
        this.bio = bio;
    }
}
