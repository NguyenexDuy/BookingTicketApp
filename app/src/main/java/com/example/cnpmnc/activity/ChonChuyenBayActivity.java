package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
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
    ChuyenBay chuyenBay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chuyen_bay);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        tabLayout = findViewById(R.id.tab_ChonChuyenBay);
        viewPager = findViewById(R.id.ChonChonBay_pager);
        adapter = new TabLayoutAdapter(this);
        ChuyenBay newChuyenBay=(ChuyenBay) getIntent(). getSerializableExtra("ThongTinChuyenBay");

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

        if (getIntent().getSerializableExtra("ThongTinChuyenBay")!=null)
        {
            Toast.makeText(this, "Co chuyen bay", Toast.LENGTH_SHORT).show();
        } else if (bundle!=null) {
            Toast.makeText(this, "Co diem den diem di", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Ngu", Toast.LENGTH_SHORT).show();
        }
        adapter.setChuyenBay(newChuyenBay);



    }
}