package com.github.kirivasile.tt_android_task1;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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

    List<CardData> dataList;

    public RVAdapter(List<CardData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_element, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardData elemData = dataList.get(position);
        holder.imageView.setImageResource(elemData.photoId);
        holder.textView.setText(elemData.cardText);
        if (position % 2 != 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#aaaaaa"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
