package com.skpissay.baseproject.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class ProductsAll {

    @SerializedName("products")
    @Expose
    private List<Products> products;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
