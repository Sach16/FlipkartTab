package com.skpissay.baseproject.screens.presenters;

import android.util.Log;

import com.skpissay.baseproject.essentials.BasePresenter;
import com.skpissay.baseproject.models.FlipkartAll;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.screens.views.HomeView;

import retrofit2.Response;
import rx.Observer;

/**
 * Created by skpissay on 25/06/18.
 */

public class HomePresenter extends BasePresenter implements Observer<Response<BasicResponse>> {
    private HomeView mHomeActivityView;

    public HomePresenter(HomeView homeActivityView) {
        mHomeActivityView = homeActivityView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onCompleted() {
        mHomeActivityView.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mHomeActivityView.onError(e.getMessage());
    }

    @Override
    public void onNext(Response<BasicResponse> response) {
        mHomeActivityView.onPlaces(response);
    }

    Observer<Response<FlipkartAll>> FlipkartAllListener = new Observer<Response<FlipkartAll>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response<FlipkartAll> flipkartAllResponse) {
            mHomeActivityView.onAffiliateList(flipkartAllResponse);
        }
    };

    void getPlaces(String accessToken, Long omsUserId) {
        unSubscribeMain();
        subscribe(mHomeActivityView.getPlaces(accessToken, omsUserId), this);
    }

    public void getAffiliateList() {
        unSubscribeMain();
        subscribe(mHomeActivityView.getAffiliateList(), FlipkartAllListener);
    }

    @Override
    public void onDestroy() {
        unSubscribeMain();
        unSubscribeSecondary();
        unSubscribeSearch();
    }
}
