package com.github.kirivasile.tt_android_task1.gallery_activity;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kirivasile.tt_android_task1.R;
import com.github.kirivasile.tt_android_task1.detail_activity.DetailActivity;
import com.github.kirivasile.tt_android_task1.models.Technology;

import java.util.ArrayList;

/**
 * Created by Kirill on 19.03.2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {
    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public CardViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cv);
            imageView = (ImageView) view.findViewById(R.id.picture);
            textView = (TextView) view.findViewById(R.id.cardtext);
        }
    }

    private ArrayList<Technology> mData;
    private GalleryActivity mParentActivity;

    public static final String DATA_TAG = "Technology";
    public static final String POSITION_TAG = "Position";

    public RVAdapter(GalleryActivity mainActivity, ArrayList<Technology> data) {
        this.mParentActivity = mainActivity;
        this.mData = data;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_element, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        Technology tech = mData.get(position);
        ImageLoader imageLoader = new ImageLoader(tech.getImageUrl(), holder.imageView, -1);
        imageLoader.execute();
        holder.textView.setText(tech.getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mParentActivity.getApplicationContext(),
                        DetailActivity.class);
                intent.putParcelableArrayListExtra(DATA_TAG, mData);
                intent.putExtra(POSITION_TAG, position);
                mParentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
