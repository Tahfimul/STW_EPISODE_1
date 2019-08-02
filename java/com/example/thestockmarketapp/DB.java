//Author: Tahfimul Latif

package com.example.thestockmarketapp;

import android.support.annotation.NonNull;
import com.google.firebase.database.*;

import java.util.Arrays;
import java.util.Hashtable;

public class DB {

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private static Hashtable<String, Stock> dataset = new Hashtable<>();

    private Repo repo;

    public DB(Repo repo)
    {
        this.repo = repo;
    }

    public void getStocks()
    {
        ref.child("Tickers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ticker:dataSnapshot.getChildren()) {
                    if (ticker.getChildrenCount() == 5) {
                        String t = ticker.getKey();
                        String close = "Close: " + ticker.child("4 DOT  close").getValue().toString().replace(" DOT ", ".");
                        String open = "Open: " + ticker.child("1 DOT  open").getValue().toString().replace(" DOT ", ".");
                        String high = "High: " + ticker.child("2 DOT  high").getValue().toString().replace(" DOT ", ".");
                        String low = "Low: " + ticker.child("3 DOT  low").getValue().toString().replace(" DOT ", ".");
                        String volume = "Volume: " + ticker.child("5 DOT  volume").getValue().toString().replace(" DOT ", ".");
                        Stock data = new Stock(t, close, open, high, low, volume);
                        dataset.put(t, data);
                    }
                }

                repo.setStocks(dataset);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
