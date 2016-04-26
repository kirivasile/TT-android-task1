package com.github.kirivasile.tt_android_task1.detail_activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kirivasile.tt_android_task1.R;
import com.github.kirivasile.tt_android_task1.gallery_activity.ImageLoader;
import com.github.kirivasile.tt_android_task1.models.Technology;

/**
 * Created by Kirill on 26.04.2016.
 */
public class DetailFragment extends Fragment {
    private ImageView mImageView;
    private TextView mInfoView;
    private String mInfo;
    private String mUrl;
    private Bitmap mPicture = null; //Кэширование картинки, чтобы не загружать второй раз

    private static final String INFO_TAG = "Info";
    private static final String URL_TAG = "Url";

    public static DetailFragment newInstance(Technology tech) {
        DetailFragment result = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(INFO_TAG, tech.getInfo());
        args.putString(URL_TAG, tech.getImageUrl());
        result.setArguments(args);
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = getArguments().getString(INFO_TAG);
        mUrl = getArguments().getString(URL_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        mImageView = (ImageView) v.findViewById(R.id.fragment_picture);
        if (mPicture == null) {
            int maxWidth = getDisplayWidth() - dpToPx(20);
            ImageLoader imageLoader = new ImageLoader(mUrl, mImageView, maxWidth);
            //mPicture = checkSizes(mPicture);
            mImageView.setImageBitmap(mPicture);
            imageLoader.execute();
        } else {
            //mPicture = checkSizes(mPicture);
            mImageView.setImageBitmap(mPicture);
        }
        mInfoView = (TextView) v.findViewById(R.id.fragment_text);
        mInfoView.setText(mInfo);
        return v;
    }

    private int getDisplayWidth() {
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int dpToPx(int dp){
        Resources resources = getActivity().getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
