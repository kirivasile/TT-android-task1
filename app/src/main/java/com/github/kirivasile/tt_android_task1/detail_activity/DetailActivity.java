package com.github.kirivasile.tt_android_task1.detail_activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.github.kirivasile.tt_android_task1.R;
import com.github.kirivasile.tt_android_task1.gallery_activity.RVAdapter;
import com.github.kirivasile.tt_android_task1.models.Technology;

import java.util.ArrayList;

public class DetailActivity extends FragmentActivity {
    private ArrayList<Technology> mData;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mData = intent.getParcelableArrayListExtra(RVAdapter.DATA_TAG);
        int position = intent.getIntExtra(RVAdapter.POSITION_TAG, 0);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);
    }

    private class DetailPagerAdapter extends FragmentStatePagerAdapter {
        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DetailFragment.newInstance(mData.get(position));
        }

        @Override
        public int getCount() {
            return mData.size();
        }


    }
}
