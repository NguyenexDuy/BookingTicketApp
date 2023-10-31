package com.example.cnpmnc.activity;

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

import com.example.cnpmnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    String userID;
    private EditText edtEmail,edtPass,edtRepass,edtHoten,edtGioiTinh,edtNgaySinh,edtSDT,edtQuocTich;
    private Button BtnDangKy,BtnReSendCode;

    private TextView Layout_Dang_Ky;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        initUI();
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

                String strHoten = edtHoten.getText().toString();
                String strGioiTinh = edtGioiTinh.getText().toString();
                String strNgaySinh = edtNgaySinh.getText().toString();
                String strSDT = edtSDT.getText().toString();
                String strQuocTich = edtQuocTich.getText().toString();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();

                if (strHoten.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền họ và tên", Toast.LENGTH_SHORT).show();

                }  if (strNgaySinh.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền ngày sinh", Toast.LENGTH_SHORT).show();

                }if (strGioiTinh.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền giới tính", Toast.LENGTH_SHORT).show();

                }
                if (strSDT.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền số điện thoại", Toast.LENGTH_SHORT).show();

                }  if (strQuocTich.isEmpty()) {
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền quốc tịch", Toast.LENGTH_SHORT).show();

                }if (strEmail.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Vui lòng điền Email", Toast.LENGTH_SHORT).show();
                }if (TextUtils.isEmpty(strPass) || strPass.length() < 6) {
                    Toast.makeText(DangKyActivity.this, "Mật khẩu phải có ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();

                }if (!strPass.equals(strRePass)) {
                    // Passwords do not match
                    Toast.makeText(DangKyActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(strEmail, strPass)
                            .addOnCompleteListener(DangKyActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Map<String, String> khachHang=new HashMap<>();
                                        khachHang.put("Gmail",strEmail);
                                        khachHang.put("Password",strPass);
                                        khachHang.put("HoTen",strHoten);
                                        khachHang.put("GioiTinh",strGioiTinh);
                                        khachHang.put("NgaySinh",strNgaySinh);
                                        khachHang.put("Sdt",strSDT);
                                        khachHang.put("QuocTich",strQuocTich);

                                        firestore.collection("User")
                                                .add(khachHang)
                                                .addOnSuccessListener(documentReference -> {

                                                })
                                                .addOnFailureListener(e -> {

                                                });
                                        Toast.makeText(DangKyActivity.this, "Tạo tài khoản thành công.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DangKyActivity.this,DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                        FirebaseUser User = mAuth.getCurrentUser();
                                        sendEmailVerification(User);
                                        User.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(DangKyActivity.this, "Mã xác thực đã được gửi", Toast.LENGTH_SHORT).show();



                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(DangKyActivity.this, "Tài khoản Gmail không tồn tại", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(DangKyActivity.this, "Tạo tài khoản thất bại.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }});

    }
    private void checkIfEmailVerified(FirebaseUser user) {
        if (user.isEmailVerified()) {
            // Tài khoản đã được xác thực email
            Intent intent = new Intent(DangKyActivity.this,HomePageActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Tài khoản chưa được xác thực email
            Toast.makeText(DangKyActivity.this, "Vui lòng xác thực email để đăng nhập", Toast.LENGTH_SHORT).show();
        }

    }
    private void sendEmailVerification(@NonNull FirebaseUser user) {
        user.sendEmailVerification()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Gửi email xác thực thành công
                        Toast.makeText(DangKyActivity.this, "Vui lòng kiểm tra email để xác thực tài khoản", Toast.LENGTH_SHORT).show();
                        checkIfEmailVerified(user);

                    } else {
                        // Gửi email xác thực thất bại
                        Toast.makeText(DangKyActivity.this, "Gửi email xác thực thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void initUI(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        firestore = FirebaseFirestore.getInstance();
        edtEmail = findViewById(R.id.edtEmailDangKy);
        edtPass = findViewById(R.id.edtPassDangKy);
        edtRepass = findViewById(R.id.edtRePassDangKy);
        BtnDangKy = findViewById(R.id.BtnDangKy);
        Layout_Dang_Ky = findViewById(R.id.layout_dangnhap);
        edtHoten = findViewById(R.id.edtHoTen);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        edtSDT = findViewById(R.id.edtSDT);
        edtQuocTich = findViewById(R.id.edtQuocTich);
        BtnReSendCode = findViewById(R.id.Btn_XacThuc);

    }
}
