package com.github.kirivasile.tt_android_task1.start_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.kirivasile.tt_android_task1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://mobevo.ext.terrhq.ru/shr/j/ru/technology.js";
        AsyncDownload asyncDownload = new AsyncDownload(this, url);
        asyncDownload.execute();

        /* Maybe it will be a better example, but in task it was mandatory to use sleep()
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
            }
        };
        handler.postDelayed(runnable, 2000); */
    }
}
