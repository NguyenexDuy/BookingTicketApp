package com.example.cnpmnc.activity;

import android.app.Activity;
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
import com.example.cnpmnc.fragment.HomePageFragment;
import com.example.cnpmnc.fragment.KhuHoiFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {

    private boolean kiemTraDangNhap = false;
    private ProgressDialog progressDialog;
    TextView Layout_Dang_Ky;
    private EditText edtEmailDangNhap,edtPassDangNhap;
    Button BtnDangNhap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dang_nhap);
        Layout_Dang_Ky=(TextView)findViewById(R.id.tv_dangky);
        BtnDangNhap=(Button)findViewById(R.id.btnDangNhap);

        initUI();
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
                if(strEmail.isEmpty()){
                    Toast.makeText(DangNhapActivity.this, "Vui lòng điền Email",
                            Toast.LENGTH_SHORT).show();
                } else if (strPass.isEmpty()) {
                    Toast.makeText(DangNhapActivity.this, "Vui lòng điền Mật khẩu",
                            Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(strEmail, strPass)
                            .addOnCompleteListener(DangNhapActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        if (kiemTraDangNhap) {
                                            getSupportFragmentManager().beginTransaction()
                                                    .replace(R.id.content_fm, new KhuHoiFragment())
                                                    .commit();
                                        } else {
                                            Intent intent = new Intent(DangNhapActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
//                                        Intent intent = getIntent();
//                                        String callingFragment = intent.getStringExtra("callingFragment");
//                                        if ("KhuHoiFragment".equals(callingFragment)) {
//                                            // Nếu đến từ KhuHoiFragment, chuyển đến KhuHoiFragment
//                                            Intent khuHoiIntent = new Intent(DangNhapActivity.this, KhuHoiFragment.class);
//                                            startActivity(khuHoiIntent);
//                                        } else {
//                                            // Ngược lại, chuyển đến HomePageActivity
//                                            Intent homePageIntent = new Intent(DangNhapActivity.this, HomePageActivity.class);
//                                            startActivity(homePageIntent);
//                                        }
//
//                                        finish();
                                    } else {
                                        // Nếu đăng nhập thất bại, hiển thị thông báo cho người dùng.
                                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
    public void setLoginForSearch(boolean loginForSearch) {
        kiemTraDangNhap = loginForSearch;
    }
    private void initUI(){

        Layout_Dang_Ky = findViewById(R.id.tv_dangky);

        edtEmailDangNhap = findViewById(R.id.edtEmailDangNhap);
        edtPassDangNhap= findViewById(R.id.edtPassDangNhap);
        BtnDangNhap = findViewById(R.id.btnDangNhap);
        progressDialog = new ProgressDialog(this);

    }
}