package com.github.kirivasile.tt_android_task1.start_activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.github.kirivasile.tt_android_task1.gallery_activity.GalleryActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kirill on 19.03.2016.
 */
public class AsyncDownload extends AsyncTask<String, Void, String> {
    private static final String ERROR_TAG = "AsnycDonloadException";
    private Activity mActivity;
    private String mStringUrl;

    public static final String TECH_TAG = "TechnologyModel";

    public AsyncDownload(Activity mActivity, String stringUrl) {
        this.mActivity = mActivity;
        this.mStringUrl = stringUrl;
    }

    @Override
    protected String doInBackground(String... params) {
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.e("Exception", "doInBackground: " + e.toString() );
        }
        return null;*/
        try {
            URL url = new URL(mStringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                String errorMessage = "Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
                Log.e(ERROR_TAG, errorMessage);
                return null;
            }
            InputStream input = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line);
            }
            return lines.toString();
        } catch (Exception e) {
            Log.e(ERROR_TAG, "doInBackground: ", e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String stringJson) {
        super.onPostExecute(stringJson);
        if (stringJson == null) {
            mActivity.finish();
            return;
        }
        Intent intent = new Intent(mActivity.getApplicationContext(), GalleryActivity.class);
        intent.putExtra(TECH_TAG, stringJson);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
