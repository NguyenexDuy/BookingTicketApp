package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
=======
>>>>>>> parent of 12bff98 (Merge branch 'main' of https://github.com/racingboy2/BookingTicketApp)

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cnpmnc.R;

public class ThongTinKhachhangActivity extends AppCompatActivity {

    TextView tvThongTinGheNgoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khachhang);
<<<<<<< HEAD
        AnhXa();

        soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
        tv_SoLuongHangKhach.setText(String.valueOf(soLuongHangKhach));
        HangKhachAdapter hangKhach2_12TuoiAdapter=new HangKhachAdapter(numberTreEm2_12Tuoi, getListUser2_12Tuoi());
        HangKhachAdapter hangKhachNguoiLonAdapter=new HangKhachAdapter(numberNguoiLon, getListUserNguioLon());
        HangKhachAdapter hangKhach2Tuoi=new HangKhachAdapter(numberTreEm2Tuoi, getListUser2Tuoi());

        rcvNguoiLon.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        rcvTreEm2Tuoi.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        rcvTreEm2_12Tuoi.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        rcvNguoiLon.setAdapter(hangKhachNguoiLonAdapter);
        rcvTreEm2Tuoi.setAdapter(hangKhach2Tuoi);
        rcvTreEm2_12Tuoi.setAdapter(hangKhach2_12TuoiAdapter);

=======
        tvThongTinGheNgoi=findViewById(R.id.tvThongTinGheNgoi);
>>>>>>> parent of 12bff98 (Merge branch 'main' of https://github.com/racingboy2/BookingTicketApp)
        tvThongTinGheNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ThongTinChoNgoiActivity.class);
                startActivity(intent);
            }
        });
    }
}