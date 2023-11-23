package com.example.cnpmnc.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.HangKhach;

import java.util.ArrayList;
import java.util.Calendar;

public class NhapThongTinHangKhachNguoiLonActivity extends AppCompatActivity {
    EditText edtDanhXung,edtHoTen,edtDateOfBirth,edtGmail,edtSDT;
    Button btnHoanThanh;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_khach_hang);
        AnhXa();
        position=getIntent().getIntExtra("position",0);
        btnHoanThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String hoTen = edtHoTen.getText().toString();
//                DiaDiem diaDiem = DiaDiem.getInstance();
//                ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachNguoiLonList();
//                if (position >= 0 && position < hangKhachList.size()) {
//                    HangKhach hangKhach = hangKhachList.get(position);
//                    hangKhach.setHoTen(hoTen);
//                } else {
//                    Toast.makeText(NhapThongTinHangKhachNguoiLonActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
//                }
//                diaDiem.setHangKhachNguoiLonList(hangKhachList);
//                onBackPressed();
//                finish();
//            }
                String hoTen1 = edtHoTen.getText().toString();
                String danhXung1 = edtDanhXung.getText().toString();
                String dateOfBirth = edtDateOfBirth.getText().toString();
                String gmail1 = edtGmail.getText().toString();
                String sdt = edtSDT.getText().toString();
                if(TextUtils.isEmpty(danhXung1)){
                    showError(edtDanhXung,"Vui lòng nhập thông tin");
                    return;
                }
                if(TextUtils.isEmpty(dateOfBirth)){
                    showError(edtDateOfBirth,"Vui lòng nhập ngày sinh");
                    return;
                }
                if(TextUtils.isEmpty(hoTen1)){
                    showError(edtHoTen,"Vui lòng nhập họ tên");
                    return;
                }
                if(TextUtils.isEmpty(gmail1)){
                    showError(edtGmail,"Vui lòng nhập Email!!");
                    edtGmail.requestFocus();
                    return;
                }
                if(!gmail1.contains("@gmail.com")){
                    showError(edtGmail, "Email đăng ký không hợp lệ");
                    edtGmail.requestFocus();
                    return;
                }
                if (sdt.length() < 8 || sdt.length() > 10) {
                    showError(edtSDT, "Số điện thoại phải có từ 8 đến 10 ký tự.");
                }

                else {
                    DiaDiem diaDiem = DiaDiem.getInstance();
                    ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachNguoiLonList();

                    if (position >= 0 && position < hangKhachList.size()) {
                        HangKhach hangKhach = hangKhachList.get(position);
                        hangKhach.setHoTen(hoTen1);
                    } else {
                        Toast.makeText(NhapThongTinHangKhachNguoiLonActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                    }

                    diaDiem.setHangKhachNguoiLonList(hangKhachList);
                    onBackPressed();
                    finish();
                }
            }
        });
    }
    private void AnhXa()
    {
        edtDanhXung=findViewById(R.id.edtDanhXung);
        edtHoTen=findViewById(R.id.edtHoTen);
        edtDateOfBirth=findViewById(R.id.edtDateOfBirth);
        edtGmail=findViewById(R.id.edtGmail);
        edtSDT=findViewById(R.id.edtPhoneNumber);
        btnHoanThanh=findViewById(R.id.btnHoanThanh);

        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });
    }
    private void openDialog1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(NhapThongTinHangKhachNguoiLonActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                edtDateOfBirth.setText(selectedDate);

                // Kiểm tra ràng buộc tuổi từ 18 đến 55
                int age = getAge(year, month, day);
                if (age < 18 || age > 80) {
                    Toast.makeText(NhapThongTinHangKhachNguoiLonActivity.this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                    edtDateOfBirth.setText(null);
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
}