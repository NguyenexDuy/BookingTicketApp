package com.example.cnpmnc.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {

    AppCompatButton ToLogin;

    EditText edtEmailDki,edtPasworDK, edtPrePass,edtHoTen,edtGioiTinh,edtNgaySinh,edtSDT,edtQuocTich;
    AppCompatButton btdangKi;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private Spinner spinnerGioiTinh,spinnerQuocTich;
    private ArrayAdapter<String> gioiTinhAdapter;
    private ArrayAdapter<String> quoctichAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        spinnerGioiTinh =findViewById(R.id.spinnerGioiTinhDangKi);

        gioiTinhAdapter = new ArrayAdapter<>(getApplication().getApplicationContext(), R.layout.spinner_item);
        gioiTinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGioiTinh.setAdapter(gioiTinhAdapter);
        String[] gioiTinhOptions = new String[]{"Nam", "Nữ", "Khác"};
        gioiTinhAdapter.addAll(gioiTinhOptions);


        spinnerQuocTich =findViewById(R.id.spinnerQuocTichDangKi);
        quoctichAdapter = new ArrayAdapter<>(getApplication().getApplicationContext(), R.layout.spinner_item);
        quoctichAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuocTich.setAdapter(quoctichAdapter);
        String[] quocTichOptions = new String[]{"VietNam", "USA", "China","EngLand","France","Germany","Italy","Spain","Canada","Korea","Iran"};
        quoctichAdapter.addAll(quocTichOptions);

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
        edtHoTen =findViewById(R.id.edtHoTenDangKi);
        edtNgaySinh = findViewById(R.id.edtNgaySinhDangKi);
        edtSDT = findViewById(R.id.edtSDTDangKi);

        btdangKi = findViewById(R.id.BtnDangKy);
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });

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
        String hoten = edtHoTen.getText().toString();
        String ngaysinh = edtNgaySinh.getText().toString();
        String gioitinh = spinnerGioiTinh.getSelectedItem().toString();
        String quoctich =spinnerQuocTich.getSelectedItem().toString();
        String sdt = edtSDT.getText().toString();

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
        if (sdt.length() < 8 || sdt.length() > 10) {
            showError(edtSDT, "Số điện thoại phải có từ 8 đến 10 ký tự.");
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
                                            DocumentReference dr=firestore.collection("KhachHang").document(nguoidung.getUid());
                                            Map<String,Object> nguoidunginfo=new HashMap<>();
                                            nguoidunginfo.put("Gmail",email);
                                            nguoidunginfo.put("MatKhau",pass);
                                            nguoidunginfo.put("HoTen",hoten);
                                            nguoidunginfo.put("GioiTinh",gioitinh);
                                            nguoidunginfo.put("NgaySinh",ngaysinh);
                                            nguoidunginfo.put("Sdt",sdt);
                                            nguoidunginfo.put("QuocTich",quoctich);
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
    private void openDialog1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DangKyActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String thismonth;
                if (month + 1 < 10) {
                    thismonth = "0" + String.valueOf(month + 1);
                } else {
                    thismonth = String.valueOf(month + 1);
                }
                String selectedDate = String.valueOf(year) + "-" + thismonth + "-" + String.valueOf(day);
                edtNgaySinh.setText(selectedDate);

                // Kiểm tra ràng buộc tuổi từ 18 đến 55
                int age = getAge(year, month, day);
                if (age < 18 || age > 70) {
                    Toast.makeText(DangKyActivity.this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                    edtNgaySinh.setText(null);
                    openDialog1();
                }
            }
        }, year, month, day);


        datePickerDialog.show();
    }
    private int getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
    private void showError(EditText mEdt, String s)
    {
        mEdt.setError(s);
    }
    private boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$");
    }
}