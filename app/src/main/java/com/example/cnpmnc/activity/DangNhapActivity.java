package com.example.cnpmnc.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnpmnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private TextView Layout_Dang_Ky;
    private EditText edtEmailDangNhap,edtPassDangNhap;
    Button BtnDangNhap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initUI();
        initListener();
    }
    private void initUI(){

        Layout_Dang_Ky = findViewById(R.id.layout_dangky);

        edtEmailDangNhap = findViewById(R.id.edtEmailDangNhap);
        edtPassDangNhap= findViewById(R.id.edtPassDangNhap);
        BtnDangNhap = findViewById(R.id.btnDangNhap);
        progressDialog = new ProgressDialog(this);
    }
    private void initListener(){

        Layout_Dang_Ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });
        BtnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmailDangNhap.getText().toString().trim();
                String strPass = edtPassDangNhap.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(strEmail, strPass)
                        .addOnCompleteListener(DangNhapActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Intent intent = new Intent(DangNhapActivity.this,HomePageActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(DangNhapActivity.this, "Đang nhập thất bại.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}
