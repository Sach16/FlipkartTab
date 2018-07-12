package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class Products {

    @SerializedName("productBaseInfoV1")
    @Expose
    private ProductBaseInfoV1 productBaseInfoV1;

    public ProductBaseInfoV1 getProductBaseInfoV1() {
        return productBaseInfoV1;
    }

    public void setProductBaseInfoV1(ProductBaseInfoV1 productBaseInfoV1) {
        this.productBaseInfoV1 = productBaseInfoV1;
    }
}
