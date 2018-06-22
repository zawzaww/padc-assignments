package com.zawzaw.tedtalksassignment.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 13/06/2018.
 */

public class TagVO {

    @SerializedName("talk_id")
    private int tagId;

    @SerializedName("tag")
    private String tag;

    @SerializedName("description")
    private String description;

    public int getTagId() {
        return tagId;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

}
