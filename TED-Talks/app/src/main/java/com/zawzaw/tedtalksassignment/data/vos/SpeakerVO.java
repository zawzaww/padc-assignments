package com.zawzaw.tedtalksassignment.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 13/06/2018.
 */

public class SpeakerVO {

    @SerializedName("speaker_id")
    private int speakerId;

    @SerializedName("name")
    private String name;

    public int getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }

}
