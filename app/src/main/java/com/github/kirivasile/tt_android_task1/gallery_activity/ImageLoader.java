package com.github.kirivasile.tt_android_task1.gallery_activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kirill on 26.04.2016.
 */
public class ImageLoader extends AsyncTask<Void, Void, Bitmap>{
    private String mUrl;
    private ImageView mImageView;
    private int mMaxWidth;
    private static final String ERROR_TAG = "ImageLoaderException";

    public ImageLoader(String url, ImageView imageView, int maxWidth) {
        this.mUrl = url;
        this.mImageView = imageView;
        this.mMaxWidth = maxWidth;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e(ERROR_TAG, "ImageLoader: failed to load picture");
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap == null) {
            return;
        }
        if (mMaxWidth != -1) {
            bitmap = checkSizes(bitmap);
        }
        mImageView.setImageBitmap(bitmap);
    }

    // Проверка на размеры картинки (Ширина экрана - 20 dp)
    private Bitmap checkSizes(Bitmap bmOriginal) {
        int imageWidth = bmOriginal.getWidth();
        int imageHeight = bmOriginal.getHeight();
        if (imageWidth >= mMaxWidth) {
            int newImageWidth = imageWidth;
            imageHeight = newImageWidth * imageHeight / imageWidth;
            imageWidth = newImageWidth;
        }
        return Bitmap.createScaledBitmap(bmOriginal, imageWidth, imageHeight, false);
    }


}
