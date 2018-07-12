package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class V110 {

    @SerializedName("resourceName")
    @Expose
    private String resourceName;
    @SerializedName("put")
    @Expose
    private Object put;
    @SerializedName("post")
    @Expose
    private Object post;
    @SerializedName("get")
    @Expose
    private String get;
    @SerializedName("deltaGet")
    @Expose
    private String deltaGet;
    @SerializedName("delete")
    @Expose
    private Object delete;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Object getPut() {
        return put;
    }

    public void setPut(Object put) {
        this.put = put;
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Object post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getDeltaGet() {
        return deltaGet;
    }

    public void setDeltaGet(String deltaGet) {
        this.deltaGet = deltaGet;
    }

    public Object getDelete() {
        return delete;
    }

    public void setDelete(Object delete) {
        this.delete = delete;
    }

}
