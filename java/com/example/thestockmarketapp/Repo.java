//Author: Tahfimul Latif

package com.example.thestockmarketapp;

import java.util.Hashtable;

public class Repo {


    private DB db = new DB(this);

    private ViewModel vm;

    public Repo(ViewModel vm)
    {
        this.vm = vm;
    }

    public void getStocks()
    {
        db.getStocks();
    }

    public void setStocks(Hashtable<String, Stock> dataset)
    {
        vm.setStocks(dataset);
    }


}
