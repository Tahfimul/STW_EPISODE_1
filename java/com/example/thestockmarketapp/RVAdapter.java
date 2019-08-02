//Author: Tahfimul Latif

package com.example.thestockmarketapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.VH> {

    private Hashtable<String, Stock> preDataset;
    private ArrayList<String> dataset = new ArrayList<>();


    public class VH extends RecyclerView.ViewHolder {

        private TextView ticker;
        private TextView open;
        private TextView close;
        private TextView high;
        private TextView low;
        private TextView volume;

        public VH(@NonNull View itemView) {
            super(itemView);
            ticker = itemView.findViewById(R.id.ticker);
            open = itemView.findViewById(R.id.open);
            close = itemView.findViewById(R.id.close);
            high = itemView.findViewById(R.id.high);
            low = itemView.findViewById(R.id.low);
            volume = itemView.findViewById(R.id.volume);
        }

        public void bind(Stock data)
        {
            ticker.setText(data.getTicker());
            open.setText(data.getOpen());
            close.setText(data.getClose());
            high.setText(data.getHigh());
            low.setText(data.getLow());
            volume.setText(data.getVolume());
        }


    }

    public RVAdapter(Hashtable<String, Stock> dataset) {
        preDataset = dataset;
        convertToArrayList();
    }

    private void convertToArrayList()
    {
        dataset.addAll(preDataset.keySet());

        notifyDataSetChanged();
    }

    public void updateDataset(Stock data)
    {
        preDataset.put(data.getTicker(), data);

        if (dataset.contains(data.getTicker()))
            dataset.set(dataset.indexOf(data.getTicker()), data.getTicker());
        else
            dataset.add(data.getTicker());

        notifyItemChanged(dataset.indexOf(data.getTicker()));
    }


    @NonNull
    @Override
    public RVAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_card, viewGroup, false);

        VH vh = new VH(rl);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.VH vh, int i) {

        vh.bind(preDataset.get(dataset.get(i)));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
