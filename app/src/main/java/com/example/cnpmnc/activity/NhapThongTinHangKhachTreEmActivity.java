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

public class NhapThongTinHangKhachTreEmActivity extends AppCompatActivity {

    EditText edtHoTenTreEm,edtDateOfBirthTreEm;
    Button btnHoanThanhTreEm;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_hang_khach_tre_em);
        AnhXa();
        position=getIntent().getIntExtra("position1",0);
        btnHoanThanhTreEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=edtHoTenTreEm.getText().toString();
                DiaDiem diaDiem=DiaDiem.getInstance();
                ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachTreEm2_12TList();
                if (position >= 0 && position < hangKhachList.size()) {
                    HangKhach hangKhach = hangKhachList.get(position);
                    hangKhach.setHoTen(hoten);
                } else {
                    Toast.makeText(NhapThongTinHangKhachTreEmActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                }
                diaDiem.setHangKhachTreEm2_12TList(hangKhachList);
                Intent intent = new Intent(NhapThongTinHangKhachTreEmActivity.this, ThongTinKhachhangActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void AnhXa()
    {
        edtHoTenTreEm=findViewById(R.id.edtHoTenTreEm);
        edtDateOfBirthTreEm=findViewById(R.id.edtDateOfBirthTreEm);
        btnHoanThanhTreEm=findViewById(R.id.btnHoanThanhTreEm);
    }
}