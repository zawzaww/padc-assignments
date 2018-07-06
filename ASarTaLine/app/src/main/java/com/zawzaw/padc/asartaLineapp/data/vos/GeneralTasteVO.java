package com.zawzaw.padc.asartaLineapp.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zawzaw on 06/07/2018.
 */

public class GeneralTasteVO {

    @SerializedName("tasteId")
    private String tasteId;

    @SerializedName("taste")
    private String taste;

    @SerializedName("tasteDesc")
    private String tasteDesc;

    public String getTasteId() {
        return tasteId;
    }

    public String getTaste() {
        return taste;
    }

    public String getTasteDesc() {
        return tasteDesc;
    }

}
