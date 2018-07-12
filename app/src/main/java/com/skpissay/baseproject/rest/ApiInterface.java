package com.skpissay.baseproject.rest;


import com.skpissay.baseproject.models.FlipkartAll;
import com.skpissay.baseproject.models.ProductsAll;
import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.models.Wikipedia;
import com.skpissay.baseproject.rest.response.BasicResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public interface ApiInterface {

    @GET("w/api.php")
    Observable<Response<Wikipedia>> getWikiList(@Query("format") String format,
                                                @Query("prop") String prop,
                                                @Query("generator") String generator,
                                                @Query("redirects") int redirects,
                                                @Query("formatversion") int formatversion,
                                                @Query("piprop") String piprop,
                                                @Query("pithumbsize") int pithumbsize,
                                                @Query("pilimit") int pilimit,
                                                @Query("wbptterms") String wbptterms,
                                                @Query("gpssearch") String gpssearch,
                                                @Query("gpslimit") int gpslimit,
                                                @Query("action") String action);

    @GET("affiliate/api/affliatek.json")
    Observable<Response<FlipkartAll>> getAffiliateList();

    //"affiliate/1.0/feeds/affliatek/category/7jv.json?expiresAt=1531350620598&sig=7726335df6e6675321fc470c629a1325"
    @Headers({
            "Fk-Affiliate-Id: affliatek",
            "Fk-Affiliate-Token: 9fc7f597c35b4e4e93cc7bbe40956f86",
    })
    @GET("affiliate/1.0/feeds/affliatek/category/{path}")
    Observable<Response<ProductsAll>> getProductList(@Path("path") String path,
                                                     @Query("expiresAt") String id,
                                                     @Query("sig") String token);
}
