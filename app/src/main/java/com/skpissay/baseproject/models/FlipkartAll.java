package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class FlipkartAll {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("apiGroups")
    @Expose
    private ApiGroups apiGroups;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApiGroups getApiGroups() {
        return apiGroups;
    }

    public void setApiGroups(ApiGroups apiGroups) {
        this.apiGroups = apiGroups;
    }

}
