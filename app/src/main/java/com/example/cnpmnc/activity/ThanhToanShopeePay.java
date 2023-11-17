package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class ThanhToanShopeePay extends AppCompatActivity {
    private TextView countdownTextView;
    private Button startCountdownButton;
    private CountDownTimer countDownTimer;
    private final long startTimeInMillis = 60000; // 1 phút (1:00) expressed in milliseconds
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopeepay); // Thay thế your_layout bằng layout của bạn

        startCountdownButton = findViewById(R.id.btnConfirm); // Thay thế R.id.startCountdownButton bằng ID của Button

        startCountdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanShopeePay.this, ThanhToanShopeePay2.class);
                intent.putExtra("startTimer", true);
                startActivity(intent);
            }
        });
    }
}