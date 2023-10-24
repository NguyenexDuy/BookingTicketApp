package com.example.cnpmnc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;

public class DangNhapActivity extends AppCompatActivity {

    private TextView Layout_Dang_Ky;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initListener();
    }
    private void initUI(){
        Layout_Dang_Ky = findViewById(R.id.layout_dangky);
    }
    private void initListener(){

        Layout_Dang_Ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}
