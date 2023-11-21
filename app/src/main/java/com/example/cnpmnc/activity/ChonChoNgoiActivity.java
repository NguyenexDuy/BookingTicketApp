    package com.example.cnpmnc.activity;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Parcelable;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.cnpmnc.R;

    import com.example.cnpmnc.adapter.GheAdapter;

    import com.example.cnpmnc.adapter.HangKhachChonGheAdapter;
    import com.example.cnpmnc.model.ChuyenBay;
    import com.example.cnpmnc.model.DiaDiem;
    import com.example.cnpmnc.model.Ghe;
    import com.example.cnpmnc.model.HangKhach;
    import com.example.cnpmnc.model.HangKhachDataHolder;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.Map;

    public class ChonChoNgoiActivity extends AppCompatActivity {

        private DiaDiem diaDiem;
        int soLuongGhe,soLuongHangKhach,numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi;
        private Button btn_tiepTuc;

        private TextView tv_diemDi,tv_diemDen,tv_ngayDi,tv_tongGiaVe;
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

            chuyenBay=(ChuyenBay) getIntent().getSerializableExtra("ChuyenBayDT");
//            Bundle bundle = getIntent().getExtras();
//            if(bundle != null){
//                String loaighe = bundle.getString("LoaiGhe","");
//                GetAllGhe(loaighe);
//            }
            GetAllGhe();
            tv_diemDi.setText(chuyenBay.getDiemDi());
            tv_diemDen.setText(chuyenBay.getDiemDen());
            tv_ngayDi.setText(chuyenBay.getNgayDi());
            tv_tongGiaVe.setText(chuyenBay.getGiaVe());
            ArrayList<HangKhach> listNguoiLon = DiaDiem.getInstance().getHangKhachNguoiLonList();
            ArrayList<HangKhach> listTreoEm2_12 = DiaDiem.getInstance().getHangKhachTreEm2_12TList();
            ArrayList<HangKhach> listTreoEmDuoi2 = DiaDiem.getInstance().getHangKhachTreEmDuoi2TList();
            ArrayList<HangKhach> AllKhachHang=new ArrayList<>();
            AllKhachHang.addAll(listNguoiLon);
            AllKhachHang.addAll(listTreoEm2_12);
            AllKhachHang.addAll(listTreoEmDuoi2);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(ChonChoNgoiActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rcv_hangKhachChonCHo.setLayoutManager(layoutManager1);
            hangKhachChonGheAdapter=new HangKhachChonGheAdapter(ChonChoNgoiActivity.this,AllKhachHang);
            rcv_hangKhachChonCHo.setAdapter(hangKhachChonGheAdapter);
            ghes=new ArrayList<>();
            rcv_choNgoi.setLayoutManager(new GridLayoutManager(this,6));
            gheAdapter=new GheAdapter(ChonChoNgoiActivity.this,ghes,AllKhachHang.size());

            gheAdapter.setOnSeatSelectedListener(new GheAdapter.OnSeatSelectedListener() {
                @Override
                public void onSeatSelected(long seatNumber) {
                    HangKhachChonGheAdapter adapter= (HangKhachChonGheAdapter) rcv_hangKhachChonCHo.getAdapter();
                    if(adapter!=null){
                        adapter.setSelectedSeat(seatNumber);
                    }
//                    String currentSeatType = gheAdapter.getCurrentSeatType();
//                    Toast.makeText(ChonChoNgoiActivity.this, "Loại ghế: " + currentSeatType, Toast.LENGTH_SHORT).show();
                }
            });

            rcv_choNgoi.setAdapter(gheAdapter);

            btn_tiepTuc=findViewById(R.id.btn_tiepTuc);
            btn_tiepTuc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });


            HangKhachDataHolder dataHolder = HangKhachDataHolder.getInstance();
            dataHolder.setHangKhachList(AllKhachHang);
        }
        private void GetAllGhe(){

            firebaseFirestore.collection("ghe")
                    .whereEqualTo("IdChuyenBay",String.valueOf(chuyenBay.getIdChuyenBay()))
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult())
                    {
                        String idGhe=documentSnapshot.getId();
                        String idChuyenBay=(String)documentSnapshot.get("IdChuyenBay");
                        Long soGhe= documentSnapshot.getLong("soGhe");
                        String loaighe = (String) documentSnapshot.get("loaighe");
                        Boolean state=(Boolean) documentSnapshot.get("state");


                        Ghe ghe=new Ghe(idGhe,idChuyenBay, soGhe,loaighe,state);
                        ghes.add(ghe);
                    }
                    Collections.sort(ghes, new Comparator<Ghe>() {
                        @Override
                        public int compare(Ghe ghe, Ghe ghe2) {
                            return Long.compare(ghe.getSoGhe(),ghe2.getSoGhe());
                        }
                    });
                    gheAdapter.notifyDataSetChanged();
                }
            });
        }


        private void AnhXa()
        {

            firebaseFirestore=FirebaseFirestore.getInstance();
            tv_ngayDi=findViewById(R.id.tv_ngayDi);
            tv_diemDi=findViewById(R.id.tv_diemDi);
            tv_tongGiaVe=findViewById(R.id.tv_tongGiaVe);
            tv_diemDen=findViewById(R.id.tv_diemDen);
            rcv_choNgoi=findViewById(R.id.rcv_choNgoi);
            rcv_hangKhachChonCHo=findViewById(R.id.rcv_hangKhachChonCHo);
            numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
            numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
            numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
            soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
        }

    }