//Author: Tahfimul Latif

package com.example.thestockmarketapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    private ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vm = ViewModelProviders.of(this).get(ViewModel.class);

        vm.setActivity(this);

        vm.getStockUpdates().observe(this, new Observer<Stock>() {
            @Override
            public void onChanged(@Nullable Stock stock) {
                vm.updateRVDataset(stock);
            }
        });
    }
}
