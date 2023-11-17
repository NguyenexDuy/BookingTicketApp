package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class PaymentOptions extends AppCompatActivity {
    TextView tvMoMo, tvShopeePay, tvATM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        tvMoMo = findViewById(R.id.tvMoMo); // Thay thế R.id.startCountdownButton bằng ID của Button
        tvShopeePay = findViewById(R.id.tvShopeePay);
        tvATM = findViewById(R.id.tvATM);
        tvATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentOptions.this, ThanhToanATM.class);
                startActivity(intent);
            }
        });
        tvMoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentOptions.this, ThanhToanMoMo.class);
                startActivity(intent);
            }
        });
        tvShopeePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentOptions.this, ThanhToanShopeePay.class);
                startActivity(intent);
            }
        });
    }

}
