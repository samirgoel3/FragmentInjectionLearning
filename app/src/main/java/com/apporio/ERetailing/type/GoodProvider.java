package com.apporio.ERetailing.type;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.apporio.ERetailing.R;
import com.shizhefei.view.multitype.ItemViewProvider;

/**
 * Created by LuckyJayce on 2016/8/8.
 */
public class GoodProvider extends ItemViewProvider<Good> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int providerType) {
        return new RecyclerView.ViewHolder(inflater.inflate(R.layout.item_good, parent, false)) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Good good) {

    }
}
