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

public class NhapThongTinHangKhachTreEmActivity extends AppCompatActivity {

    EditText edtHoTenTreEm,edtDateOfBirthTreEm;
    Button btnHoanThanhTreEm;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_hang_khach_tre_em);
        AnhXa();
        position=getIntent().getIntExtra("position1",0);
        btnHoanThanhTreEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoTen1 = edtHoTenTreEm.getText().toString();
                String dateOfBirth = edtDateOfBirthTreEm.getText().toString();


                if(TextUtils.isEmpty(dateOfBirth)){
                    showError(edtDateOfBirthTreEm,"Vui lòng nhập ngày sinh");
                    return;
                }
                if(TextUtils.isEmpty(hoTen1)){
                    showError(edtHoTenTreEm,"Vui lòng nhập họ tên");
                    return;
                }


                else {
                    DiaDiem diaDiem = DiaDiem.getInstance();
                    ArrayList<HangKhach> hangKhachList = diaDiem.getHangKhachNguoiLonList();

                    if (position >= 0 && position < hangKhachList.size()) {
                        HangKhach hangKhach = hangKhachList.get(position);
                        hangKhach.setHoTen(hoTen1);
                    } else {
                        Toast.makeText(NhapThongTinHangKhachTreEmActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
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
        edtHoTenTreEm=findViewById(R.id.edtHoTenTreEm);
        edtDateOfBirthTreEm=findViewById(R.id.edtDateOfBirthTreEm);
        btnHoanThanhTreEm=findViewById(R.id.btnHoanThanhTreEm);
        edtDateOfBirthTreEm.setOnClickListener(new View.OnClickListener() {
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(NhapThongTinHangKhachTreEmActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                edtDateOfBirthTreEm.setText(selectedDate);

                // Kiểm tra ràng buộc tuổi từ 18 đến 55
                int age = getAge(year, month, day);
                if (age <= 0 || age >= 12) {
                    Toast.makeText(NhapThongTinHangKhachTreEmActivity.this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                    edtDateOfBirthTreEm.setText(null);
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