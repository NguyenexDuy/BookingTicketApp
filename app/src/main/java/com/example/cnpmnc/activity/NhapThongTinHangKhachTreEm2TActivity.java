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

public class NhapThongTinHangKhachTreEm2TActivity extends AppCompatActivity {

    int position;
    EditText edtHoTenTreEm2T,edtDateOfBirthTreEm2T;
    Button btnHoanThanhTreEm2T;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_hang_khach_tre_em2_tactivity);
        position=getIntent().getIntExtra("position2",0);
        AnhXa();
        btnHoanThanhTreEm2T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=edtHoTenTreEm2T.getText().toString();
                DiaDiem diaDiem=DiaDiem.getInstance();
                ArrayList<HangKhach> hangKhaches=diaDiem.getHangKhachTreEmDuoi2TList();
                if(position>=0&&position<hangKhaches.size())
                {
                    HangKhach hangKhach =hangKhaches.get(position);
                    hangKhach.setHoTen(hoten);
                }
                else {
                    Toast.makeText(NhapThongTinHangKhachTreEm2TActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                }
                diaDiem.setHangKhachTreEmDuoi2TList(hangKhaches);
                onBackPressed();
                finish();
            }
        });

    }

    private void AnhXa()
    {
        edtHoTenTreEm2T=findViewById(R.id.edtHoTenTreEm2T);
        edtDateOfBirthTreEm2T=findViewById(R.id.edtDateOfBirthTreEm2T);
        btnHoanThanhTreEm2T=findViewById(R.id.btnHoanThanhTreEm2T);
        
    }
}