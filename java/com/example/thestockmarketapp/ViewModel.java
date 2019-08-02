package com.example.thestockmarketapp;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Hashtable;

public class ViewModel extends android.arch.lifecycle.ViewModel {

    private RecyclerView rv;
    private MainActivity activity;
    private Repo repo = new Repo(this);
    private RVAdapter adapter;

    public MutableLiveData<Stock> getStockUpdates()
    {
        return new LiveData();
    }

    public void setActivity(MainActivity activity)
    {
        rv=activity.findViewById(R.id.rv);
        this.activity = activity;

        getStocks();
    }

    private void getStocks()
    {
        repo.getStocks();
    }

    public void setStocks(Hashtable<String, Stock> dataset)
    {
        initRV(dataset);
    }

    public void initRV(Hashtable<String, Stock> dataset)
    {
        rv.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);

        rv.setLayoutManager(layoutManager);

        adapter = new RVAdapter(dataset);

        rv.setAdapter(adapter);
    }

    public void updateRVDataset(Stock data)
    {
        adapter.updateDataset(data);
    }
}
