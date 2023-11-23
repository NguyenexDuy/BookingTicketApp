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

public class NhapThongTinHangKhachTreEm2TActivity extends AppCompatActivity {

    int position;
    EditText edtHoTenTreEm2T,edtDateOfBirthTreEm2T;
    Button btnHoanThanhTreEm2T;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_hang_khach_tre_em2_tactivity);
        position=getIntent().getIntExtra("position2",0);
        AnhXa();
        btnHoanThanhTreEm2T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen1 = edtHoTenTreEm2T.getText().toString();
                String dateOfBirth = edtDateOfBirthTreEm2T.getText().toString();

                if(TextUtils.isEmpty(dateOfBirth)){
                    showError(edtDateOfBirthTreEm2T,"Vui lòng nhập ngày sinh");
                    return;
                }
                if(TextUtils.isEmpty(hoTen1)){
                    showError(edtHoTenTreEm2T,"Vui lòng nhập họ tên");
                    return;
                }
                else {
                    DiaDiem diaDiem = DiaDiem.getInstance();
                    ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachNguoiLonList();

                    if (position >= 0 && position < hangKhachList.size()) {
                        HangKhach hangKhach = hangKhachList.get(position);
                        hangKhach.setHoTen(hoTen1);
                    } else {
                        Toast.makeText(NhapThongTinHangKhachTreEm2TActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
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
        edtHoTenTreEm2T=findViewById(R.id.edtHoTenTreEm2T);
        edtDateOfBirthTreEm2T=findViewById(R.id.edtDateOfBirthTreEm2T);
        btnHoanThanhTreEm2T=findViewById(R.id.btnHoanThanhTreEm2T);
        edtDateOfBirthTreEm2T.setOnClickListener(new View.OnClickListener() {
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(NhapThongTinHangKhachTreEm2TActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                edtDateOfBirthTreEm2T.setText(selectedDate);

                // Kiểm tra ràng buộc tuổi từ 18 đến 55
                int age = getAge(year, month, day);
                if (age > 2 || age <= 0) {
                    Toast.makeText(NhapThongTinHangKhachTreEm2TActivity.this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                    edtDateOfBirthTreEm2T.setText(null);
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