package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cnpmnc.R;

import java.util.ArrayList;
import java.util.List;

public class ChonChoNgoiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cho_ngoi);

        RecyclerView recyclerView = findViewById(R.id.rcv);

        // Create a list of items (replace this with your actual list of data)
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            items.add("Item " + i);
        }

        // Set up RecyclerView with GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(new ItemAdapter(items));
    }
}