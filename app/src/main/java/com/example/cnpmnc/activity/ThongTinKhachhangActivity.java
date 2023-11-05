package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.HangKhachAdapter;
import com.example.cnpmnc.model.HangKhach;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ThongTinKhachhangActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNguoiLon, recyclerViewTreEm;
    private LinearLayout btn_chonChoNgoi;
    private int number;
    HangKhachAdapter hangKhachAdapter;
    TextView tvThongTinGheNgoi, tv_giaChuyenBay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khachhang);
        AnhXa();
         number=5;

       hangKhachAdapter=new HangKhachAdapter(number, getListUser());
        recyclerViewNguoiLon.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        recyclerViewNguoiLon.setAdapter(hangKhachAdapter);
        tvThongTinGheNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ThongTinChoNgoiActivity.class);
                startActivity(intent);
            }
        });
        btn_chonChoNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ChonChoNgoiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa()
    {
        tvThongTinGheNgoi=findViewById(R.id.tvThongTinGheNgoi);
        btn_chonChoNgoi=findViewById(R.id.btn_chonChoNgoi);
        tv_giaChuyenBay=findViewById(R.id.tv_giaChuyenBay);
        recyclerViewNguoiLon =findViewById(R.id.rvNguoiLon);
        recyclerViewTreEm = findViewById(R.id.rvTreEm);

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