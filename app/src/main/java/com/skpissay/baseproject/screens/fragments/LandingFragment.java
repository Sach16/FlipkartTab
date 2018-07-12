package com.skpissay.baseproject.screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.skpissay.baseproject.R;
import com.skpissay.baseproject.assist.Constants;
import com.skpissay.baseproject.baseclasses.BaseActivity;
import com.skpissay.baseproject.baseclasses.BaseFragment;
import com.skpissay.baseproject.di.components.DaggerLandingComponent;
import com.skpissay.baseproject.di.modules.LandingModule;
import com.skpissay.baseproject.models.ApiListings;
import com.skpissay.baseproject.models.Page;
import com.skpissay.baseproject.models.Product;
import com.skpissay.baseproject.models.Products;
import com.skpissay.baseproject.models.ProductsAll;
import com.skpissay.baseproject.models.User;
import com.skpissay.baseproject.models.Wikipedia;
import com.skpissay.baseproject.rest.ApiInterface;
import com.skpissay.baseproject.rest.response.BasicResponse;
import com.skpissay.baseproject.screens.activities.HomeActivity;
import com.skpissay.baseproject.screens.activities.WebviewActivity;
import com.skpissay.baseproject.screens.presenters.HomePresenter;
import com.skpissay.baseproject.screens.presenters.LandingPresenter;
import com.skpissay.baseproject.screens.ui.helper.UsersAdapter;
import com.skpissay.baseproject.screens.ui.interfaces.RecyclerUsersListener;
import com.skpissay.baseproject.screens.views.HomeView;
import com.skpissay.baseproject.screens.views.LandingView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by skpissay on 25/06/18.
 */

public class LandingFragment extends BaseFragment implements LandingView, RecyclerUsersListener{

    public static final String ACCESS_TOKEN = "261bab595885e6bf8bb38e5a19795d25832d4fdb";
    public static final String Id = "affliatek";
    public static final String Token = "9fc7f597c35b4e4e93cc7bbe40956f86";

    @Nullable
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    LandingPresenter mLandingPresenter;

    @Inject
    ApiInterface mApiInterface;

    @Inject
    UsersAdapter mUsersRecycAdapter;

    private ApiListings mApiListings;
    private List<User> mUsers;
    private boolean m_cLoading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager m_cLayoutManager;

    public static LandingFragment newInstance(int pPosition, String pKey, ApiListings pObect) {
        LandingFragment lPotUserHomePublicFragment = new LandingFragment();

        Bundle args = new Bundle();
        args.putInt("Position", pPosition);
        args.putString("KEY", pKey);
        args.putString("ApiListings", (new Gson()).toJson(pObect));
        lPotUserHomePublicFragment.setArguments(args);

        return lPotUserHomePublicFragment;
    }

    @Override
    protected void handleUIMessage(Message pObjMessage) {
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.landing_fragment;
    }

    @Override
    protected void initViews(View view) {
        DaggerLandingComponent.builder()
                .homeActivityComponent(((HomeActivity) m_cObjMainActivity).getComponent())
                .landingModule(new LandingModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void init() {
        mUsers = new ArrayList<>();
        m_cLayoutManager = new LinearLayoutManager(m_cObjMainActivity);
        m_cLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(m_cLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = m_cLayoutManager.getChildCount();
                    totalItemCount = m_cLayoutManager.getItemCount();
                    pastVisiblesItems = m_cLayoutManager.findFirstVisibleItemPosition();

//                    int page = totalItemCount / 15;
                    if (m_cLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            m_cLoading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
//                            int lpage = page + 1;
//                            page = lpage;
//                            doPagination(lpage);
                        }
                    }
                }
            }
        });

        //Call Api

//        mLandingPresenter.getWikiList("json",
//                "pageimages|pageterms",
//                "prefixsearch",
//                1,
//                2,
//                "thumbnail",
//                50,
//                10,
//                "description",
//                "sdkjcn",
//                10,
//                "query");

        mApiListings = (new Gson()).fromJson(getArguments().getString("ApiListings"), ApiListings.class);
        String url = mApiListings.getAirConditioners().getAvailableVariants().getV110().getGet().split("/category/")[1];
        String path = url.split("\\?")[0];
        String uuuu = url.split("\\?")[1];
        String lexp = uuuu.split("&")[0];
        String lsig = uuuu.split("&")[1];
        String expiresAt = lexp.split("=")[1];
        String sig = lsig.split("=")[1];
        mLandingPresenter.getProductList(path,
                expiresAt,
                sig);

    }

    @Override
    protected String getEmptyTitleText() {
        return null;
    }

    @Override
    protected String getEmptyDescText() {
        return null;
    }

    @Override
    protected int getEmptyImageResource() {
        return 0;
    }

    @Override
    protected View.OnClickListener getErrorButtonClickListener() {
        return null;
    }

    @Override
    protected String getErrorText() {
        return null;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public Observable<Response<Wikipedia>> getWikiList(String format, String prop, String generator, int redirects, int formatversion, String piprop, int pithumbsize, int pilimit, String wbptterms, String gpssearch, int gpslimit, String action) {
        return mApiInterface.getWikiList(format,
                prop,
                generator,
                redirects,
                formatversion,
                piprop,
                pithumbsize,
                pilimit,
                wbptterms,
                gpssearch,
                gpslimit,
                action);
    }

    @Override
    public void onWikiList(Response<Wikipedia> response) {
        if (response.code() == 200) {
            mUsersRecycAdapter.setItemClickListener(this);
//            mUsersRecycAdapter.changeData(((Wikipedia)response.body()).getQuery().getPages());
            mRecyclerView.setAdapter(mUsersRecycAdapter);
        }

    }

    @Override
    public Observable<Response<ProductsAll>> getProductList(String path, String id, String token) {
        return mApiInterface.getProductList(path,
                id,
                token);
    }

    @Override
    public void onProductList(Response<ProductsAll> response) {
        if (response.code() == 200) {
            mUsersRecycAdapter.setItemClickListener(this);
            mUsersRecycAdapter.changeData(((ProductsAll)response.body()).getProducts());
            mRecyclerView.setAdapter(mUsersRecycAdapter);
        }
    }

    @Override
    public void onInfoClick(int pPostion, Products pPage, View pView) {

    }

    @Override
    public void onInfoLongClick(int pPostion, Products pPage, View pView) {

    }
}
