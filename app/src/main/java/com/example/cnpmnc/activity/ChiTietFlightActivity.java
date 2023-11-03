package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.fragment.MotChieuFragment;
import com.example.cnpmnc.model.ChuyenBay;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class ChiTietFlightActivity extends AppCompatActivity {

    private ImageView img_chiTietFight,imgcc;
    private ChuyenBay chuyenBay;
    private FirebaseFirestore db;
    private Button btn_datve;
    TextView tv_chiTietDiemDi,tv_MoTaChiTietFight,tv_DiemDapChiTiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_flight);

        Anhxa();
        chuyenBay=(ChuyenBay)getIntent().getSerializableExtra("ThongTinChuyenBay");

        Glide.with(ChiTietFlightActivity.this).load(chuyenBay.getHinhAnh()).into(img_chiTietFight);
        db=FirebaseFirestore.getInstance();

        String tv_chitietDiemDi=chuyenBay.getDiemDen();
        String tv_mota= chuyenBay.getMoTa();
        String tv_diemdapchitiet=chuyenBay.getMoTaDiemDap();
        tv_chiTietDiemDi.setText(tv_chitietDiemDi);
        tv_MoTaChiTietFight.setText(tv_mota);
        tv_DiemDapChiTiet.setText(tv_diemdapchitiet);

        btn_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietFlightActivity.this, HomePageActivity.class);
                intent.putExtra("Chuyenbay", chuyenBay);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        btn_datve =findViewById(R.id.btn_datve);
        img_chiTietFight=findViewById(R.id.img_chiTietFight);
        tv_chiTietDiemDi=findViewById(R.id.tv_chiTietDiemDi);
        tv_MoTaChiTietFight=findViewById(R.id.tv_MoTaChiTietFight);
        tv_DiemDapChiTiet=findViewById(R.id.tv_DiemDapChiTiet);
    }
}