package com.example.cnpmnc.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.HangKhachAdapter;
import com.example.cnpmnc.adapter.HangKhachChonGheAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.HangKhach;
import com.example.cnpmnc.model.HangKhachDataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachhangActivity extends AppCompatActivity {


    private RecyclerView rcvTreEm2_12Tuoi, rcvNguoiLon,rcvTreEm2Tuoi;

    private LinearLayout btn_chonChoNgoi;
    private HangKhach hangKhach;
    private int GiaVeTong;
    private Button btn_ThanhToan;
    ChuyenBay chuyenBay;
    private ImageView backve;
    private int numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi,soLuongHangKhach, price;
    TextView tvThongTinGheNgoi, tv_giaChuyenBay,tv_SoLuongHangKhach;
    FirebaseUser firebaseUser;
    private static final int LOGIN_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khachhang);
        AnhXa();
        chuyenBay=(ChuyenBay) getIntent().getSerializableExtra("ChuyenBayData");
        String ghe=String.valueOf(DiaDiem.getInstance().getSelectedSeatPosition());



        soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
        price=Integer.valueOf(chuyenBay.getGiaVe());
        GiaVeTong=price*soLuongHangKhach;
        tv_giaChuyenBay.setText(String.valueOf(GiaVeTong));
        tv_SoLuongHangKhach.setText(String.valueOf(soLuongHangKhach));


        Bundle bundle=getIntent().getExtras();
        String giaTri=bundle.getString("fragment");
        rcvNguoiLon.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        rcvTreEm2Tuoi.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));
        rcvTreEm2_12Tuoi.setLayoutManager(new LinearLayoutManager(ThongTinKhachhangActivity.this));

        setdata();

        tvThongTinGheNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ThongTinChoNgoiActivity.class);
                startActivity(intent);
            }
        });
        btn_chonChoNgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThongTinKhachhangActivity.this,ChonChoNgoiActivity.class);
                intent.putExtra("ChuyenBayDT", chuyenBay);
                startActivity(intent);
            }
        });
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(ThongTinKhachhangActivity.this, "Đã đăng nhập", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ThongTinKhachhangActivity.this, DiaDiem.getInstance().getDiemDen(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThongTinKhachhangActivity.this, PaymentOptions.class);
                    intent.putExtra("DuLieuChuyenBay", chuyenBay);
                    startActivity(intent);
                } else {
                    Toast.makeText(ThongTinKhachhangActivity.this, "Chưa đăng nhập", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinKhachhangActivity.this);
                    builder.setTitle("Xác nhận");
                    builder.setMessage("Quý khách cần phải đăng nhập để thực hiện đặt chuyến bay?");
                    builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ThongTinKhachhangActivity.this, DangNhapActivity.class);
                            intent.putExtra("callingActivity", "ThongTinKhachHangActivity");
                            startActivityForResult(intent, LOGIN_REQUEST_CODE);
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


    }

    private void AddVeMayBay()
    {

        HangKhachDataHolder dataHolder = HangKhachDataHolder.getInstance();
        ArrayList<HangKhach> hangKhachList = dataHolder.getHangKhachList();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String idChuyenBay=chuyenBay.getIdChuyenBay();
        String diemDi=chuyenBay.getDiemDi();
        String diemDen=chuyenBay.getDiemDen();
        String giaVe= String.valueOf(GiaVeTong);
        String ngayBay=chuyenBay.getNgayDi();
        String ngayVe=chuyenBay.getNgayVe();
        String gioDi=chuyenBay.getGioBatDau();
        String gioVe=chuyenBay.getGioVe();

        Map<String, Object> hangKhachData = new HashMap<>();
        for (int i = 0; i < hangKhachList.size(); i++) {
            HangKhach hangKhach = hangKhachList.get(i);
            Map<String, Object> hangKhachMap = new HashMap<>();
            hangKhachMap.put("name", hangKhach.getHoTen());
            hangKhachMap.put("type", hangKhach.getType());
            hangKhachMap.put("soGhe",hangKhach.getSoghe());
            hangKhachData.put("hangKhach_" + i, hangKhachMap);
        }
        hangKhachData.put("ChuyenBayID",idChuyenBay);
        hangKhachData.put("KhachHangID",userId);
        hangKhachData.put("diemDi",diemDi);
        hangKhachData.put("diemDen",diemDen);
        hangKhachData.put("gioDi",gioDi);
        hangKhachData.put("gioVe",gioVe);
        hangKhachData.put("giaVe",giaVe);
        hangKhachData.put("ngayBatDau",ngayBay);
        hangKhachData.put("ngayVe",ngayVe);

        db.collection("VeMayBay").add(hangKhachData).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Tải thành công", Toast.LENGTH_SHORT).show();
        })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                });
        backve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setdata();
    }

    private void setdata() {
        HangKhachAdapter hangKhach2_12TuoiAdapter=new HangKhachAdapter(numberTreEm2_12Tuoi, DiaDiem.getInstance().getHangKhachTreEm2_12TList(),ThongTinKhachhangActivity.this,2);
        HangKhachAdapter hangKhach2Tuoi=new HangKhachAdapter(numberTreEm2Tuoi, DiaDiem.getInstance().getHangKhachTreEmDuoi2TList(),ThongTinKhachhangActivity.this,3);
        HangKhachAdapter hangKhachNguoiLonAdapter=new HangKhachAdapter(numberNguoiLon, DiaDiem.getInstance().getHangKhachNguoiLonList(), ThongTinKhachhangActivity.this,1);

        rcvTreEm2Tuoi.setAdapter(hangKhach2Tuoi);
        rcvTreEm2_12Tuoi.setAdapter(hangKhach2_12TuoiAdapter);
        rcvNguoiLon.setAdapter(hangKhachNguoiLonAdapter);

    }

    private void AnhXa()
    {
        tvThongTinGheNgoi=findViewById(R.id.tvThongTinGheNgoi);
        btn_chonChoNgoi=findViewById(R.id.btn_chonChoNgoi);
        tv_giaChuyenBay=findViewById(R.id.tv_giaChuyenBay);
        backve=findViewById(R.id.backTNKH);
        rcvTreEm2_12Tuoi =findViewById(R.id.recyclerview2);
        rcvNguoiLon=findViewById(R.id.recyclerview1);
        rcvTreEm2Tuoi=findViewById(R.id.recyclerview3);
        tv_SoLuongHangKhach=findViewById(R.id.tv_SoLuongHangKhach);
        btn_ThanhToan=findViewById(R.id.btn_ThanhToan);
        numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
        numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
        numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
    }
}