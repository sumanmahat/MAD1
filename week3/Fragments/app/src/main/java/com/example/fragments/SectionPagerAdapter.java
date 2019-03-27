package com.example.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mfragmentlist= new ArrayList<>();
    private final List<String> mfragmenttitlelist= new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mfragmentlist.add(fragment);
        mfragmenttitlelist.add(title);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmenttitlelist.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return mfragmentlist.get(i);
    }

    @Override
    public int getCount() {
        return mfragmentlist.size();
    }


}
