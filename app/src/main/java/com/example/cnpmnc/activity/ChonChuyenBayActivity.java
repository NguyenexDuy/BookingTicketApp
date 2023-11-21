package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.TabLayoutAdapter;
import com.example.cnpmnc.fragment.LoaiVePhoThongFragment;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChonChuyenBayActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TabLayoutAdapter adapter;
    private TextView tvDiemDi,tvDiemDen;
    private ImageView image_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_bay);
        tvDiemDi=findViewById(R.id.tvDiemDi);
        tvDiemDen=findViewById(R.id.tvDiemDen);
        image_back=findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvDiemDi.setText(DiaDiem.getInstance().getDiemDi());
        tvDiemDen.setText(DiaDiem.getInstance().getDiemDen());
        tabLayout = findViewById(R.id.tab_ChonChuyenBay);
        viewPager = findViewById(R.id.ChonChonBay_pager);
        adapter = new TabLayoutAdapter(this);

        // Thiết lập adapter cho ViewPager2
        viewPager.setAdapter(adapter);
        // Kết nối TabLayout với ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Phổ Thông");
                    break;
                case 1:
                    tab.setText("Thương gia");
                    break;
                default:
                    tab.setText(position + 1);
            }
        }).attach();

    }
}