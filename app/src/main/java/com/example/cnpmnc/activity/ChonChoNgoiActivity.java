package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;

import com.example.cnpmnc.adapter.GheAdapter;

import com.example.cnpmnc.adapter.HangKhachChonGheAdapter;
import com.example.cnpmnc.adapter.ItemAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.Ghe;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChonChoNgoiActivity extends AppCompatActivity implements ItemAdapter.OnSeatClickListener{

    private DiaDiem diaDiem;
    int soLuongGhe,soLuongHangKhach,numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi;
    private Button btn_tiepTuc;

    private TextView tv_diemDi,tv_diemDen,tv_ngayDi;
    private RecyclerView rcv_hangKhachChonCHo,rcv_choNgoi;
    private HangKhachChonGheAdapter hangKhachChonGheAdapter;
    private FirebaseFirestore firebaseFirestore;
    private GheAdapter gheAdapter;
    private ChuyenBay chuyenBay;
    private ArrayList<Ghe> ghes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_cho_ngoi);
        AnhXa();

        if(DiaDiem.getInstance().getIdChuyenBay()!=null)
        {
            Toast.makeText(this, "co id", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, "khong co id", Toast.LENGTH_SHORT).show();
        }
        tv_diemDi.setText(DiaDiem.getInstance().getDiemDi());
        tv_diemDen.setText(DiaDiem.getInstance().getDiemDen());
        tv_ngayDi.setText(DiaDiem.getInstance().getNgayDi());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ChonChoNgoiActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcv_hangKhachChonCHo.setLayoutManager(layoutManager1);
        hangKhachChonGheAdapter=new HangKhachChonGheAdapter(ChonChoNgoiActivity.this,DiaDiem.getInstance().getAllHangKhach(soLuongHangKhach), soLuongHangKhach);
        rcv_hangKhachChonCHo.setAdapter(hangKhachChonGheAdapter);
        ghes=new ArrayList<>();
        GetAllGhe();
        gheAdapter=new GheAdapter(ChonChoNgoiActivity.this,ghes);
        rcv_choNgoi.setAdapter(gheAdapter);
        rcv_choNgoi.setLayoutManager(new GridLayoutManager(this,6));




        btn_tiepTuc=findViewById(R.id.btn_tiepTuc);
        btn_tiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int selectedSeatPosition = DiaDiem.getInstance().getSelectedSeatPosition();

                // Truyền vị trí ghế vào DiaDiem
//                DiaDiem.getInstance().setSelectedSeatPosition(selectedSeatPosition);
//                Toast.makeText(ChonChoNgoiActivity.this, String.valueOf(selectedSeatPosition), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(ChonChoNgoiActivity.this, ThongTinKhachhangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetAllGhe(){

        firebaseFirestore.collection("ghe").whereEqualTo("IdChuyenBay",DiaDiem.getInstance().getIdChuyenBay()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot documentSnapshot: task.getResult())
                {
                    String idGhe=documentSnapshot.getId();
                    String idChuyenBay=(String)documentSnapshot.get("IdChuyenBay");
                    Boolean state=(Boolean) documentSnapshot.get("state");
                    Ghe ghe=new Ghe(idGhe,idChuyenBay,state);
                    ghes.add(ghe);

                }
                gheAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onSeatClick(int position) {
        DiaDiem.getInstance().setSelectedSeatPosition(position);
    }
    private void AnhXa()
    {

        firebaseFirestore=FirebaseFirestore.getInstance();
        tv_ngayDi=findViewById(R.id.tv_ngayDi);
        tv_diemDi=findViewById(R.id.tv_diemDi);
        tv_diemDen=findViewById(R.id.tv_diemDen);

        rcv_hangKhachChonCHo=findViewById(R.id.rcv_hangKhachChonCHo);
        numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
        numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
        numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
        soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
    }

}