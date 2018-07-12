package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class ImageUrls {


    @SerializedName("200x200")
    @Expose
    private String _200x200;
    @SerializedName("400x400")
    @Expose
    private String _400x400;
    @SerializedName("800x800")
    @Expose
    private String _800x800;

    public String get200x200() {
        return _200x200;
    }

    public void set200x200(String _200x200) {
        this._200x200 = _200x200;
    }

    public String get400x400() {
        return _400x400;
    }

    public void set400x400(String _400x400) {
        this._400x400 = _400x400;
    }

    public String get800x800() {
        return _800x800;
    }

    public void set800x800(String _800x800) {
        this._800x800 = _800x800;
    }

}
