package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    TextView btnThongTinChoNgoi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        btnThongTinChoNgoi = findViewById(R.id.ThongTinGheNgoi);
        btnThongTinChoNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongTinKhachHangActivity.this,ThongTinChoNgoi.class);
                startActivity(intent);
            }
        });
    }
}
