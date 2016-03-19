package com.github.kirivasile.tt_android_task1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill on 15.03.2016.
 */
public class CardData {
    int photoId;
    String cardText;

    public CardData(int photoId, String cardText) {
        this.photoId = photoId;
        this.cardText = cardText;
    }

    public static List<CardData> initData() {
        List<CardData> dataList = new ArrayList<>();
        for (int i = 0; i < 1000000; ++i) {
            dataList.add(new CardData(R.drawable.wow_icon, "Sample text"));
        }
        return dataList;
    }
}
