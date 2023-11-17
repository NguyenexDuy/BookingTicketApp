package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class ThanhToanShopeePay2 extends AppCompatActivity {
    private TextView countdownTextView;
    EditText edtCode;
    private CountDownTimer countDownTimer;
    private final long startTimeInMillis = 60000; // 1 phút expressed in milliseconds
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopeepay2); // Thay thế your_next_layout bằng layout của bạn

        countdownTextView = findViewById(R.id.tvTime); // Thay thế R.id.countdownTextView bằng ID của TextView


        edtCode = findViewById(R.id.edtCode);
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Không cần thực hiện gì ở đây
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kiểm tra nếu độ dài của văn bản vượt quá 5 ký tự
                if (s.length() > 5) {
                    // Hiển thị thông báo lỗi
                    edtCode.setError("Code không quá 5 số");
                } else {
                    // Xóa thông báo lỗi nếu văn bản hợp lệ
                    edtCode.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần thực hiện gì ở đây
            }
        });

        Intent intent = getIntent();
        boolean startTimer = intent.getBooleanExtra("startTimer", false);

        if (startTimer) {
            startTimer();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(startTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateCountdownText(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                countdownTextView.setText("00:00");
            }
        }.start();

        timerRunning = true;
    }

    private void updateCountdownText(long milliseconds) {
        int minutes = (int) (milliseconds / 1000) / 60;
        int seconds = (int) (milliseconds / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        countdownTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}