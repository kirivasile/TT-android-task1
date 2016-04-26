package com.github.kirivasile.tt_android_task1.gallery_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.kirivasile.tt_android_task1.R;
import com.github.kirivasile.tt_android_task1.models.Technology;
import com.github.kirivasile.tt_android_task1.start_activity.AsyncDownload;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    public static final String ERROR_TAG = "GalleryActivityException";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv);

        Intent intent = getIntent();
        String stringJson = intent.getStringExtra(AsyncDownload.TECH_TAG);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(this, parseJson(stringJson));
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Technology> parseJson(String stringJson) {
        ArrayList<Technology> result = new ArrayList<>();
        try {
            if (stringJson.length() == 0) {
                return result;
            }
            JSONObject jsonObject = new JSONObject(stringJson);
            jsonObject = jsonObject.getJSONObject("technology");
            List<String> keyList = new ArrayList<>();
            Iterator keysIterator = jsonObject.keys();
            while (keysIterator.hasNext()) {
                String key = (String)keysIterator.next();
                keyList.add(key);
            }
            for (String key: keyList) {
                JSONObject objectWithNum = jsonObject.getJSONObject(key);
                int id = objectWithNum.getInt("id");
                String imageUrl = objectWithNum.getString("picture");
                String title = objectWithNum.getString("title");
                String info = objectWithNum.getString("info");
                result.add(new Technology(id, imageUrl, title, info));
            }
        } catch (JSONException e) {
            Log.e(ERROR_TAG, "Failed to make json " + e.toString());
        }
        return result;
    }
}
