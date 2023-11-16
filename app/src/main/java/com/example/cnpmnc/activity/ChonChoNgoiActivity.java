package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.HangKhachChonGheAdapter;
import com.example.cnpmnc.adapter.ItemAdapter;
import com.example.cnpmnc.model.DiaDiem;

import java.util.ArrayList;
import java.util.List;

public class ChonChoNgoiActivity extends AppCompatActivity implements ItemAdapter.OnSeatClickListener{

    private DiaDiem diaDiem;
    int soLuongGhe,soLuongHangKhach,numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi;
    private Button btn_tiepTuc;
    private RecyclerView rcv_hangKhachChonCHo;
    private HangKhachChonGheAdapter hangKhachChonGheAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cho_ngoi);
        AnhXa();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ChonChoNgoiActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcv_hangKhachChonCHo.setLayoutManager(layoutManager1);
        hangKhachChonGheAdapter=new HangKhachChonGheAdapter(ChonChoNgoiActivity.this,DiaDiem.getInstance().getAllHangKhach(soLuongHangKhach), soLuongHangKhach);
        rcv_hangKhachChonCHo.setAdapter(hangKhachChonGheAdapter);

        RecyclerView recyclerView = findViewById(R.id.rcv);
        soLuongGhe=Integer.valueOf(diaDiem.getInstance().getSoLuongGheTrong());
        List<String> items = new ArrayList<>();
        for (int i = 0; i < soLuongGhe; i++) {
            items.add("Item " + i);
        }


        ItemAdapter itemAdapter = new ItemAdapter(items, position -> {

            Toast.makeText(ChonChoNgoiActivity.this, "Selected seat: " + position, Toast.LENGTH_SHORT).show();
            DiaDiem.getInstance().setSelectedSeatPosition(position);
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setAdapter(itemAdapter);


        btn_tiepTuc=findViewById(R.id.btn_tiepTuc);
        btn_tiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedSeatPosition = DiaDiem.getInstance().getSelectedSeatPosition();

                // Truyền vị trí ghế vào DiaDiem
                DiaDiem.getInstance().setSelectedSeatPosition(selectedSeatPosition);
                Toast.makeText(ChonChoNgoiActivity.this, String.valueOf(selectedSeatPosition), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ChonChoNgoiActivity.this, ThongTinKhachhangActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSeatClick(int position) {
        DiaDiem.getInstance().setSelectedSeatPosition(position);
    }
    private void AnhXa()
    {
        rcv_hangKhachChonCHo=findViewById(R.id.rcv_hangKhachChonCHo);
        numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
        numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
        numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
        soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
    }

}