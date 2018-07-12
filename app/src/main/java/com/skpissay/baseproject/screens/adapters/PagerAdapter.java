package com.skpissay.baseproject.screens.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.skpissay.baseproject.baseclasses.BaseFragment;
import com.skpissay.baseproject.models.ApiListings;
import com.skpissay.baseproject.screens.fragments.LandingFragment;

/**
 * Created by S.K. Pissay on 11,July,2018.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int m_cNumOfTabs;
    String m_cId;
    private ApiListings m_cObject;
    private String m_cLessFromWhere;
    private String m_cGoOffline;

    //write inner fragment items below

    public BaseFragment m_cObjFragmentBase;

    public PagerAdapter(FragmentManager pFragment, BaseFragment pObjFragmentBase,
                                    int pNumOfTabs, String pId, ApiListings pObject,
                                    String pGoOffline) {
        super(pFragment);
        this.m_cNumOfTabs = pNumOfTabs;
        this.m_cObjFragmentBase = pObjFragmentBase;
        this.m_cId = pId;
        this.m_cObject = pObject;
        this.m_cGoOffline = pGoOffline;
    }

    @Override
    public Fragment getItem(int position) {
        if (0 <= position && position <= 12) {
            m_cObjFragmentBase = LandingFragment.newInstance(position, m_cId, m_cObject);
            return m_cObjFragmentBase;
        } else {
                return null;
        }
    }

    @Override
    public int getCount() {
        return m_cNumOfTabs;
    }
}
