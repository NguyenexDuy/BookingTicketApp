package com.example.cnpmnc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.cnpmnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    AppCompatButton ToLogin;

    EditText edtEmailDki,edtPasworDK, edtPrePass;
    AppCompatButton btdangKi;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        //Chuyển qua trang đăng nhập
        ToLogin = findViewById(R.id.layout_dangnhap);
        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        edtEmailDki = findViewById(R.id.edtEmailDangKy);
        edtPasworDK = findViewById(R.id.edtPassDangKy);
        edtPrePass = findViewById(R.id.edtRePassDangKy);

        btdangKi = findViewById(R.id.BtnDangKy);

        btdangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register() {
        String email, pass,repass;
        email = edtEmailDki.getText().toString();
        pass = edtPasworDK.getText().toString();
        repass= edtPrePass.getText().toString();

        if(TextUtils.isEmpty(email)){
            showError(edtEmailDki,"Vui lòng nhập Email!!");
            edtEmailDki.requestFocus();return;
        }
        if(TextUtils.isEmpty(pass)){
            showError(edtPasworDK,"Vui lòng nhập password");
            return;
        }
        if(!pass.equals(repass)){
            showError(edtPrePass,"Mật khẩu không khớp!");
            edtPrePass.requestFocus();
            return;
        }
        if(!email.contains("@gmail.com")){
            showError(edtEmailDki, "Email đăng ký không hợp lệ");
            edtEmailDki.requestFocus();
            return;
        }
        if (!isPasswordValid(pass)) {
            showError(edtPasworDK,"Mật khẩu không đủ điều kiện");
            edtPasworDK.requestFocus();
            return;
        }
        firestore.collection("Customer").whereEqualTo("Gmail",email).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if (task.getResult() != null && !task.getResult().isEmpty()){
                                Toast.makeText(DangKyActivity.this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công!!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(DangKyActivity.this,DangNhapActivity.class));
                                            FirebaseUser nguoidung = mAuth.getCurrentUser();
                                            DocumentReference dr=firestore.collection("Customer").document(nguoidung.getUid());
                                            Map<String,Object> nguoidunginfo=new HashMap<>();
                                            nguoidunginfo.put("Gmail",email);
                                            nguoidunginfo.put("MatKhau",pass);
                                            dr.set(nguoidunginfo);
                                        }
                                        else {
                                            Toast.makeText(DangKyActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                        else {Toast.makeText(DangKyActivity.this, "Lỗi kiểm tra email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }
    private boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$");
    }
}