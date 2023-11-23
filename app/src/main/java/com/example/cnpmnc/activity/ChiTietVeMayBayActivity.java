package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.TongHopHangKhachNotifiAdapter;
import com.example.cnpmnc.model.Firebase;
import com.example.cnpmnc.model.HangKhach;
import com.example.cnpmnc.model.HangKhachDataHolder;
import com.example.cnpmnc.model.VeMayBay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class ChiTietVeMayBayActivity extends AppCompatActivity {

    VeMayBay veMayBay;
    Firebase firebase;
    private FirebaseFirestore db;
    RecyclerView rcv_HK;
    Button btn_huyVe;
    TextView tv_idUser,tv_maDatVe,tv_gioDi,tv_diemDi,tv_idDiemDi;
    TextView tv_gioDen,tv_diemDen,tv_idDiemDen,tv_tongTien, tv_ngayDi,tv_ngayVeCT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_ve_may_bay);
        AnhXa();
        LayDuLieu();
        btn_huyVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                AlertDialog.Builder builder=new AlertDialog.Builder(ChiTietVeMayBayActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Qúy khách có chắc chắn muốn hủy vé không?");
                builder.setPositiveButton("Hủy vé", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String idVeMayBay = veMayBay.getIdVe();
                        db.collection("VeMayBay").document(idVeMayBay)
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(ChiTietVeMayBayActivity.this, "Hủy vé thành công", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ChiTietVeMayBayActivity.this, "Hủy vé thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        updateSoLuongGheTrong();
                        updateBooking();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }
    private void updateBooking() {
        String idChuyenBay = veMayBay.getIdChuyenBay();
        ArrayList<Map<String, Object>> hangKhachList = veMayBay.getHangKhaches();

        for (Map<String, Object> hangKhachMap : hangKhachList) {
            Long soghe = (Long) hangKhachMap.get("soGhe");

            // Reference to the "ghe" collection and query for matching documents
            db.collection("ghe")
                    .whereEqualTo("IdChuyenBay", idChuyenBay)
                    .whereEqualTo("soGhe", soghe)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            documentSnapshot.getReference().update("isBooked", false)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("CapnhatBooking", "Cập nhật thành công cho ghế: " + soghe);
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.w("CapnhatBooking", "Lỗi khi cập nhật cho ghế: " + soghe, e);
                                    });
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.w("Capnhatghe", "Lỗi khi truy vấn danh sách ghế", e);
                    });
        }
    }
    private void updateSoLuongGheTrong() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String idChuyenBay = veMayBay.getIdChuyenBay();

        // Tìm và cập nhật số lượng ghế trống sau khi thanh toán
        db.collection("ChuyenBay")
                .document(idChuyenBay)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String soLuongGheTrong = document.getString("SoLuongGheTrong");
                            ArrayList<Map<String, Object>> hangKhachList = veMayBay.getHangKhaches();

                            int soLuongGheTrongInt = Integer.parseInt(soLuongGheTrong);
                            soLuongGheTrongInt += hangKhachList.size();
                            String updatedSoLuongGheTrong = String.valueOf(soLuongGheTrongInt);

                            db.collection("ChuyenBay")
                                    .document(idChuyenBay)
                                    .update("SoLuongGheTrong", updatedSoLuongGheTrong)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("PaymentOptions", "Số lượng ghế trống đã được cập nhật.");
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.w("PaymentOptions", "Lỗi khi cập nhật số lượng ghế trống.", e);
                                    });
                        } else {
                            Log.d("PaymentOptions", "Không tìm thấy tài liệu.");
                        }
                    } else {
                        Log.d("PaymentOptions", "Lỗi khi truy cập dữ liệu: ", task.getException());
                    }
                });
    }
    private void LayDuLieu()
    {
        veMayBay=(VeMayBay) getIntent().getSerializableExtra("VeMayBay");
        db=FirebaseFirestore.getInstance();
        String idUser= veMayBay.getIdHangKhach();
        String idVeMayBay=veMayBay.getIdChuyenBay();
        String diemDi=veMayBay.getDiemDiVe();
        String diemVe=veMayBay.getDiemDenVe();
        String ngayDi=veMayBay.getNgayBatDau();
        String gioDi=veMayBay.getGioDi();
        String ngayVe=veMayBay.getNgayVe();
        String gioVe=veMayBay.getGioVe();
        String giaVe=veMayBay.getGiaVe();
        ArrayList<Map<String, Object>> hangKhachList = veMayBay.getHangKhaches();
        TongHopHangKhachNotifiAdapter adapter=new TongHopHangKhachNotifiAdapter(this,hangKhachList);
        rcv_HK.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChiTietVeMayBayActivity.this, LinearLayoutManager.VERTICAL, false);
        rcv_HK.setLayoutManager(layoutManager);
        tv_idUser.setText(idUser);
        tv_maDatVe.setText(idVeMayBay);
        tv_diemDi.setText(diemDi);
        tv_diemDen.setText(diemVe);
        tv_tongTien.setText(giaVe);
        tv_ngayDi.setText(ngayDi);
        tv_gioDi.setText(gioDi);
        tv_gioDen.setText(gioVe);
        tv_ngayVeCT.setText(ngayVe);
        firebase.getIdSanBayByTenSanBay(veMayBay.getDiemDenVe(), new Firebase.getIdSanBayByTenSanBayCallback() {
            @Override
            public void onCallBack(String idSanBay) {
                tv_idDiemDen.setText(idSanBay);
            }
        });
        firebase.getIdSanBayByTenSanBay(veMayBay.getDiemDiVe(), new Firebase.getIdSanBayByTenSanBayCallback() {
            @Override
            public void onCallBack(String idSanBay) {
                tv_idDiemDi.setText(idSanBay);
            }
        });
//        Toast.makeText(this, String.valueOf(hangKhachList.size()), Toast.LENGTH_SHORT).show();

    }


    private void AnhXa()
    {
        firebase=new Firebase(ChiTietVeMayBayActivity.this);
        btn_huyVe=findViewById(R.id.btn_huyVe);
        rcv_HK=findViewById(R.id.rcv_HK);
        tv_ngayVeCT=findViewById(R.id.tv_ngayVeCT);
        tv_ngayDi =findViewById(R.id.tv_ngayDiCT);
        tv_idUser=findViewById(R.id.tv_idUser);
        tv_maDatVe=findViewById(R.id.tv_maDatVe);
        tv_gioDi=findViewById(R.id.tv_gioDi);
        tv_gioDen=findViewById(R.id.tv_gioDen);
        tv_diemDi=findViewById(R.id.tv_diemDiCT);
        tv_diemDen=findViewById(R.id.tv_diemDenCT);
        tv_idDiemDi=findViewById(R.id.tv_idDiemDi);
        tv_idDiemDen=findViewById(R.id.tv_idDiemDen);
        tv_tongTien=findViewById(R.id.tv_tongTien);
    }
}