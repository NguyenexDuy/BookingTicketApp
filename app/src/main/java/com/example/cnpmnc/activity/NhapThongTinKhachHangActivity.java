package com.example.cnpmnc.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.HangKhach;

public class NhapThongTinKhachHangActivity extends AppCompatActivity {

    EditText edtDanhXung,edtHoTen,edtDateOfBirth,edtGmail,edtSDT;
    Button btnHoanThanh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_khach_hang);
        AnhXa();

        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen = edtHoTen.getText().toString();
                Intent intent = new Intent(NhapThongTinKhachHangActivity.this, ThongTinKhachhangActivity.class);
                HangKhach hangKhach = new HangKhach("", hoTen, "");
                intent.putExtra("tenHangKhach",hangKhach);
                startActivity(intent);
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