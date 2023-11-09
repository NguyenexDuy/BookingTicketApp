package com.example.cnpmnc.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;
public class NhapThongTinKhachHangActivity extends AppCompatActivity {
    EditText edtDanhXung,edtHoTen,edtDateOfBirth,edtGmail,edtSDT;
    Button btnHoanThanh;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_khach_hang);
        AnhXa();
        position=getIntent().getIntExtra("position",0);
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edtHoTen.getText().toString();
                DiaDiem diaDiem = DiaDiem.getInstance();
                ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachNguoiLonList();
                if (position >= 0 && position < hangKhachList.size()) {
                    HangKhach hangKhach = hangKhachList.get(position);
                    hangKhach.setHoTen(hoTen);
                } else {
                    Toast.makeText(NhapThongTinKhachHangActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                }
                diaDiem.setHangKhachNguoiLonList(hangKhachList);
                Intent intent = new Intent(NhapThongTinKhachHangActivity.this, ThongTinKhachhangActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void AnhXa()
    {
        edtDanhXung=findViewById(R.id.edtDanhXung);
        edtHoTen=findViewById(R.id.edtHoTen);
        edtDateOfBirth=findViewById(R.id.edtDateOfBirth);
        edtGmail=findViewById(R.id.edtGmail);
        edtSDT=findViewById(R.id.edtPhoneNumber);
        btnHoanThanh=findViewById(R.id.btnHoanThanh);
    }
}