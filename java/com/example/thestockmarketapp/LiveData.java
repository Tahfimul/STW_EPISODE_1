package com.example.thestockmarketapp;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.google.firebase.database.*;
import java.util.Hashtable;

public class LiveData extends MutableLiveData<Stock> {

    private DatabaseReference db;

    public LiveData()
    {
        db = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onActive() {
        super.onActive();

        db.child("Updates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getChildrenCount()==6) {

                    String t = dataSnapshot.child("ticker").getValue().toString();
                    String close = "Close: " + dataSnapshot.child("4 DOT  close").getValue().toString().replace(" DOT ", ".");
                    String open = "Open: " + dataSnapshot.child("1 DOT  open").getValue().toString().replace(" DOT ", ".");
                    String high = "High: " + dataSnapshot.child("2 DOT  high").getValue().toString().replace(" DOT ", ".");
                    String low = "Low: " + dataSnapshot.child("3 DOT  low").getValue().toString().replace(" DOT ", ".");
                    String volume = "Volume: " + dataSnapshot.child("5 DOT  volume").getValue().toString().replace(" DOT ", ".");
                    Stock data = new Stock(t, close, open, high, low, volume);

                    setValue(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.keepSynced(true);
    }
}
