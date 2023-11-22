package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FormPersonActivity extends AppCompatActivity {
    String userEmail; // The userEmail will store the currently logged-in user's email

    EditText mSDT, memail, edten, mngaysinh;
    FirebaseFirestore db;
    Button btnluu;
    private Spinner spinnerGioiTinh, spinnerQuocTich;
    private ArrayAdapter<String> gioiTinhAdapter;
    private ArrayAdapter<String> quoctichAdapter;
    //App bar
    ActionBar actionBar;
    private final int GALLERY_REQ_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_person);
        spinnerGioiTinh =findViewById(R.id.spinnerGioiTinh);

        gioiTinhAdapter = new ArrayAdapter<>(getApplication().getApplicationContext(), R.layout.spinner_item);
        gioiTinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGioiTinh.setAdapter(gioiTinhAdapter);
        String[] gioiTinhOptions = new String[]{"Nam", "Nữ", "Khác"};
        gioiTinhAdapter.addAll(gioiTinhOptions);

        spinnerQuocTich =findViewById(R.id.spinnerQuocTich);
        quoctichAdapter = new ArrayAdapter<>(getApplication().getApplicationContext(), R.layout.spinner_item);
        quoctichAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuocTich.setAdapter(quoctichAdapter);
        String[] quocTichOptions = new String[]{"VietNam", "USA", "China","EngLand","France","Germany","Italy","Spain","Canada","Korea"};
        quoctichAdapter.addAll(quocTichOptions);
        db = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            startActivity(new Intent(FormPersonActivity.this, DangNhapActivity.class));
            return;
        }

        // Initialize views and set click listener for "Lưu" (Save) button
        initView();
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserInfoInFirestore();
            }
        });
        fetchUserInfoFromFirestore();

    }


    private void fetchUserInfoFromFirestore() {
        db.collection("KhachHang")
                .whereEqualTo("Gmail", userEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Duyệt qua tất cả các tài liệu kết quả (thường chỉ có 1)
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String hoten = document.getString("HoTen");
                                String ngaysinh = document.getString("NgaySinh");
                                String quoctich = document.getString("QuocTich");
                                String sdt = document.getString("Sdt");
                                String gioiTinh = document.getString("GioiTinh");
                                int gioiTinhIndex = gioiTinhAdapter.getPosition(String.valueOf(gioiTinh));
                                spinnerGioiTinh.setSelection(gioiTinhIndex);
                                int quocTichIndex = quoctichAdapter.getPosition(String.valueOf(quoctich));
                                spinnerQuocTich.setSelection(quocTichIndex);
                                edten.setText(hoten);
                                mngaysinh.setText(ngaysinh);
                                spinnerGioiTinh.setTag(gioiTinh);
                                mSDT.setText(sdt);
                                memail.setText(userEmail);
                                memail.setEnabled(false);
                            }
                        } else {

                        }
                    }
                });
    }

//    private void fetchUserInfoFromFirestore() {
//        DocumentReference docRef = db.collection("KhachHang").document(userEmail);
//
//        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                if (documentSnapshot.exists()) {
//                    // Document exists, retrieve data
//                    String hoten = documentSnapshot.getString("HoTen");
//                    String Email = documentSnapshot.getString("Gmail");
//                    String ngaysinh = documentSnapshot.getString("NgaySinh");
//                    String quoctich = documentSnapshot.getString("QuocTich");
//                    String sdt = documentSnapshot.getString("Sdt");
//                    String gioiTinh = documentSnapshot.getString("GioiTinh");
//                    int gioiTinhIndex = gioiTinhAdapter.getPosition(String.valueOf(gioiTinh));
//                    spinnerGioiTinh.setSelection(gioiTinhIndex);
//                    int quocTichIndex = quoctichAdapter.getPosition(String.valueOf(quoctich));
//                    spinnerQuocTich.setSelection(quocTichIndex);
//                    edten.setText(hoten);
//                    mngaysinh.setText(ngaysinh);
//                    spinnerGioiTinh.setTag(gioiTinh);
//                    mSDT.setText(sdt);
//                    memail.setText(Email);
//                    memail.setEnabled(false);
//                } else {
//                    // Document does not exist, leave EditText fields empty
//                    memail.setText("");
//
//                }
//            }
//        });
//    }
    private void initView() {
        memail = findViewById(R.id.edtEmail);
        edten = findViewById(R.id.edthovaten);
        mSDT = findViewById(R.id.edtSDT);
        mngaysinh = findViewById(R.id.edtngaysinh);
        btnluu = findViewById(R.id.btnsuatt);
        mngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog1();
            }
        });
    }
    private void updateUserInfoInFirestore() {
        String hoten = edten.getText().toString();
        String ngaysinh = mngaysinh.getText().toString();
        String gioitinh = spinnerGioiTinh.getSelectedItem().toString();
        String quoctich = spinnerQuocTich.getSelectedItem().toString();
        String sdt = mSDT.getText().toString();
        if (sdt.length() < 8 || sdt.length() > 10 || !sdt.matches("\\d+")) {
            showError(mSDT, "Số điện thoại không hợp lệ.");
            return;
        }
        Query query = db.collection("KhachHang").whereEqualTo("Gmail", userEmail);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().isEmpty()) {
                        Toast.makeText(FormPersonActivity.this, "Không tìm thấy thông tin người dùng để cập nhật.", Toast.LENGTH_SHORT).show();
                    } else {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        String documentId = document.getId();
                        if (isUserInfoChanged(document, hoten, ngaysinh, gioitinh, quoctich, sdt)) {
                            Map<String, Object> userMap = new HashMap<>();
                            userMap.put("HoTen", hoten);
                            userMap.put("NgaySinh", ngaysinh);
                            userMap.put("GioiTinh", gioitinh);
                            userMap.put("QuocTich", quoctich);
                            userMap.put("Sdt", sdt);
                            userMap.put("Gmail",userEmail);

                            db.collection("KhachHang").document(documentId)
                                    .update(userMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(FormPersonActivity.this, "Cập nhật thông tin người dùng thành công.", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(FormPersonActivity.this, "Cập nhật thông tin người dùng thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(FormPersonActivity.this, "Không có sự thay đổi trong thông tin người dùng.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(FormPersonActivity.this, "Lỗi khi kiểm tra số điện thoại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUserInfoChanged(DocumentSnapshot document, String hoten, String ngaysinh, String gioitinh, String quoctich, String sdt) {
        return !document.getString("HoTen").equals(hoten)
                || !document.getString("NgaySinh").equals(ngaysinh)
                || !document.getString("GioiTinh").equals(gioitinh)
                || !document.getString("QuocTich").equals(quoctich)
                || !document.getString("Sdt").equals(sdt);
    }


    private void openDialog1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(FormPersonActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                mngaysinh.setText(selectedDate);

                // Kiểm tra ràng buộc tuổi từ 18 đến 55
                int age = getAge(year, month, day);
                if (age < 18 || age > 70) {
                    Toast.makeText(FormPersonActivity.this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show();
                    mngaysinh.setText(null);
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
