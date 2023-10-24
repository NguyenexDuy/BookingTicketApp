package com.example.cnpmnc.activity;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class DangKyActivity extends AppCompatActivity {

    private EditText edtEmail,edtPass,edtRepass,edtHoten,edtNgaySinh,edtSDT,edtQuocTich;
    private Button BtnDangKy;

    private TextView Layout_Dang_Ky;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        initUI();
        initListener();

    }
    private void initUI(){
        edtEmail = findViewById(R.id.edtEmailDangKy);
        edtPass = findViewById(R.id.edtPassDangKy);
        edtRepass = findViewById(R.id.edtRePassDangKy);
        BtnDangKy = findViewById(R.id.BtnDangKy);
        Layout_Dang_Ky = findViewById(R.id.layout_dangky);
        edtHoten = findViewById(R.id.edtHoTen);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtSDT = findViewById(R.id.edtSDT);
        edtQuocTich = findViewById(R.id.edtQuocTich);
    }
    private void initListener(){

        Layout_Dang_Ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });
        BtnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = edtEmail.getText().toString().trim();
                String strPass = edtPass.getText().toString().trim();
                String strRePass = edtRepass.getText().toString().trim();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                if (!strPass.equals(strRePass)) {
                    // Passwords do not match
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(strEmail, strPass)
                            .addOnCompleteListener(DangKyActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information
                                        Intent intent = new Intent(DangKyActivity.this, HomePageActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(DangKyActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });





    }

    }
