package com.zawzaw.tedtalksassignment.data.vos;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 13/06/2018.
 */

public class TalksVO {

    @SerializedName("talk_id")
    private int talkId;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("durationInSec")
    private int durationInSec;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tags;

    public int getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTags() {
        return tags;
    }

}
