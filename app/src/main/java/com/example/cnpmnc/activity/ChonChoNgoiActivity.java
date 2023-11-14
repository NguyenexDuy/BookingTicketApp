package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.ItemAdapter;
import com.example.cnpmnc.model.DiaDiem;

import java.util.ArrayList;
import java.util.List;

public class ChonChoNgoiActivity extends AppCompatActivity {

    private DiaDiem diaDiem;
    int soLuongGhe;
    private Button btn_tiepTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cho_ngoi);

        RecyclerView recyclerView = findViewById(R.id.rcv);
        soLuongGhe=Integer.valueOf(diaDiem.getInstance().getSoLuongGheTrong());
        // Create a list of items (replace this with your actual list of data)
        List<String> items = new ArrayList<>();
        for (int i = 0; i < soLuongGhe; i++) {
            items.add("Item " + i);
        }

        // Set up RecyclerView with GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(new ItemAdapter(items));
        btn_tiepTuc=findViewById(R.id.btn_tiepTuc);
        btn_tiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChonChoNgoiActivity.this, ThongTinKhachhangActivity.class);
                startActivity(intent);
            }
        });
    }
}