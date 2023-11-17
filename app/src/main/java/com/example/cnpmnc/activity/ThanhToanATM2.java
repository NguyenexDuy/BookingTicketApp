package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class ThanhToanATM2 extends AppCompatActivity {
    private TextView tvBankName,tvBankName2;
    private Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm2); // Thay thế your_layout bằng layout của bạn
        tvBankName = findViewById(R.id.tvBankName);
        tvBankName2 = findViewById(R.id.tvBankName2);
        String bankName = getIntent().getStringExtra("bankName");
        tvBankName.setText(bankName);
        tvBankName2.setText(bankName);

        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM2.this, ThanhToanThanhCong.class);
                startActivity(intent);
            }
        });


    }
}