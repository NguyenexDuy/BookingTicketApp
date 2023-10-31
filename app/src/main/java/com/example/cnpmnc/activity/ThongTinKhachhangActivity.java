package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.HangKhachAdapter;
import com.example.cnpmnc.model.HangKhach;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ThongTinKhachhangActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int number;
    HangKhachAdapter hangKhachAdapter;
    TextView tvThongTinGheNgoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khachhang);
        tvThongTinGheNgoi=findViewById(R.id.tvThongTinGheNgoi);
        recyclerView=findViewById(R.id.recyclerview1);
//        int abc=getIntent().getIntExtra("Soluonghangkhach",0);
         number=5;

        HangKhachAdapter hangKhachAdapter=new HangKhachAdapter(number, getListUser());
        recyclerView.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        recyclerView.setAdapter(hangKhachAdapter);
        tvThongTinGheNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ThongTinChoNgoiActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<HangKhach> getListUser() {
        ArrayList<HangKhach> list=new ArrayList<>();
        for (int i=0;i<number;i++)
        {
            list.add(new HangKhach("",""));
        }
        return list;
    }
}