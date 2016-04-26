package com.github.kirivasile.tt_android_task1.models;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Kirill on 26.04.2016.
 */
public class Technology implements Parcelable {
    private int mId;
    private String mImageUrl;
    private String mTitle;
    private String mInfo;

    public Technology(int mId, String mImageUrl, String mTitle, String mInfo) {
        this.mId = mId;
        this.mImageUrl = "http://mobevo.ext.terrhq.ru/" + mImageUrl;
        this.mTitle = mTitle;
        this.mInfo = mInfo;
    }

    public Technology(Parcel in) {
        this.mId = in.readInt();
        this.mImageUrl = in.readString();
        this.mTitle = in.readString();
        this.mInfo = in.readString();
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getInfo() {
        return mInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mImageUrl);
        dest.writeString(mTitle);
        dest.writeString(mInfo);
    }

    public static final Parcelable.Creator<Technology> CREATOR
            = new Parcelable.Creator<Technology>() {
        public Technology createFromParcel(Parcel in) {
            return new Technology(in);
        }

        public Technology[] newArray(int size) {
            return new Technology[size];
        }
    };
}
