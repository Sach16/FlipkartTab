package com.skpissay.baseproject.screens.activities;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.skpissay.baseproject.BaseApp;
import com.skpissay.baseproject.R;
import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.di.components.DaggerHomeActivityComponent;
import com.skpissay.baseproject.di.components.HomeActivityComponent;
import com.skpissay.baseproject.di.modules.HomeActivityModule;
import com.skpissay.baseproject.models.ApiListings;
import com.skpissay.baseproject.models.FlipkartAll;
import com.skpissay.baseproject.models.Product;
import com.skpissay.baseproject.rest.ApiInterface;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.screens.adapters.PagerAdapter;
import com.skpissay.baseproject.screens.presenters.HomePresenter;
import com.skpissay.baseproject.screens.ui.assist.FragmentHelper;
import com.skpissay.baseproject.screens.views.HomeView;
import com.skpissay.baseproject.utils.CustomTabLayout;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    @Nullable
    @BindView(R.id.TAB_LAYOUT)
    CustomTabLayout mTabLayout;

    @Nullable
    @BindView(R.id.PAGER)
    ViewPager mPager;

    @Inject
    FragmentHelper mFragmentHelper;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    HomePresenter mHomePresenter;

    private HomeActivityComponent mComponent;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mComponent = DaggerHomeActivityComponent.builder()
                .homeActivityModule(new HomeActivityModule(this))
                .appComponent(((BaseApp) getApplication()).getComponent())
                .build();

        mComponent.inject(this);

//        mFragmentHelper.switchUi(FragmentHelper.LANDING, null);

        init();
    }

    private void init() {
        mHomePresenter.getAffiliateList();
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    public HomeActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onPlaces(Response<BasicResponse> response) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onDeviceDeregistrationFailed() {

    }

    @Override
    public Observable<Response<BasicResponse>> getPlaces(String pAccessToken, Long pPlaceId) {
        return null;
    }

    @Override
    public Observable<Response<FlipkartAll>> getAffiliateList() {
        return mApiInterface.getAffiliateList();
    }

    @Override
    public void onAffiliateList(Response<FlipkartAll> response) {
        System.out.print(response.body());
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getAirConditioners().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getAirCoolers().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getAudioPlayers().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getAutomotive().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getBabyCare().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getBagsWalletsBelts().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getCameraAccessories().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getCameras().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getComputerComponents().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getComputerPeripherals().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getComputerStorage().getApiName())));
        mTabLayout.addTab(mTabLayout.newTab().setText(s2l(response.body().getApiGroups().getAffiliate().getApiListings().getDesktops().getApiName())));

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),
                m_cObjFragmentBase,
                12,
                "",
                response.body().getApiGroups().getAffiliate().getApiListings(),
                null);
//        m_cPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPagerAdapter);
    }

    public static String s2l(String pSlug) {
        StringBuffer lBuff = new StringBuffer();
        String lSample = pSlug.toLowerCase().replace("_", " ");
        String[] titleArray = lSample.split(" ");
        for (String lparam : titleArray) {
            lBuff.append(Character.toUpperCase(lparam.charAt(0))).append(lparam.substring(1)).append(" ");
        }
        return lBuff.toString();
    }
}
