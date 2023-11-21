package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.TongHopHangKhachNotifiAdapter;
import com.example.cnpmnc.model.VeMayBay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class ChiTietVeMayBayActivity extends AppCompatActivity {

    VeMayBay veMayBay;
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
        Toast.makeText(this, String.valueOf(hangKhachList.size()), Toast.LENGTH_SHORT).show();

    }
    private void AnhXa()
    {
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