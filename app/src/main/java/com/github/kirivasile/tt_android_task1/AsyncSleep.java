package com.github.kirivasile.tt_android_task1;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Kirill on 19.03.2016.
 */
public class AsyncSleep extends AsyncTask<Void, Void, Void> {
    private Activity mActivity;

    public AsyncSleep(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.e("Exception", "doInBackground: " + e.toString() );
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent intent = new Intent(mActivity.getApplicationContext(), SecondActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
