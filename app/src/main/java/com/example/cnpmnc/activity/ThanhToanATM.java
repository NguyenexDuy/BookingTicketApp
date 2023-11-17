package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class ThanhToanATM extends AppCompatActivity {
    private TextView countdownTextView;
    private ImageView btnNamA, btnExim, btnMB, btnBacA, btnVP,btnSHB,btnSacom,btnVietcom,btnTech,btnVietin,btnVIB,btnTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atm); // Thay thế your_layout bằng layout của bạn

        btnNamA = findViewById(R.id.imageView4); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnNamA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "NamABank");
                startActivity(intent);
            }
        });
        btnExim = findViewById(R.id.imageView3); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnExim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "EximBank");

                startActivity(intent);
            }
        });
        btnMB = findViewById(R.id.imageView2); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "MBBank");

                startActivity(intent);
            }
        });
        btnBacA = findViewById(R.id.imageView6); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnBacA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "BacABank");

                startActivity(intent);
            }
        });
        btnVP = findViewById(R.id.imageView8); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnVP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "VPBank");

                startActivity(intent);
            }
        });
        btnSHB = findViewById(R.id.imageView7); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnSHB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "SHBBank");

                startActivity(intent);
            }
        });
        btnSacom = findViewById(R.id.imageView13); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnSacom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "Sacombank");

                startActivity(intent);
            }
        });
        btnTech = findViewById(R.id.imageView14); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnTech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "Techcombank");

                startActivity(intent);
            }
        });
        btnVietin = findViewById(R.id.imageView12); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnVietin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "Viettinbank");

                startActivity(intent);
            }
        });
        btnVIB = findViewById(R.id.imageView10); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnVIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "VIBBank");

                startActivity(intent);
            }
        });
        btnVietcom = findViewById(R.id.imageView11); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnVietcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "Vietcombank");

                startActivity(intent);
            }
        });
        btnTP = findViewById(R.id.imageView5); // Thay thế R.id.startCountdownButton bằng ID của Button

        btnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThanhToanATM.this, ThanhToanATM2.class);
                intent.putExtra("bankName", "TPBank");

                startActivity(intent);
            }
        });
    }
}