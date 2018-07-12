package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class ApiGroups {

    @SerializedName("affiliate")
    @Expose
    private Affiliate affiliate;

    public Affiliate getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(Affiliate affiliate) {
        this.affiliate = affiliate;
    }

}
