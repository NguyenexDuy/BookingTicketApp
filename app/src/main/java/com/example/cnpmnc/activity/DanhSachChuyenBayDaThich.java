package com.example.cnpmnc.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.RcvCateFlightAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class DanhSachChuyenBayDaThich extends AppCompatActivity {
    private ArrayList<ChuyenBay> danhSachYeuThich = new ArrayList<>();
    private RecyclerView rvDanhSachYeuThich;
    private RcvCateFlightAdapter danhSachYeuThichAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chuyen_bay_da_thich);

//        ArrayList<ChuyenBay> danhSachYeuThich = (ArrayList<ChuyenBay>) getIntent().getSerializableExtra("DanhSachYeuThich");

      rvDanhSachYeuThich = findViewById(R.id.rvDanhSachChuyenBayDaThich);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvDanhSachYeuThich.setLayoutManager(layoutManager);

        danhSachYeuThichAdapter = new RcvCateFlightAdapter(danhSachYeuThich,this);
        rvDanhSachYeuThich.setAdapter(danhSachYeuThichAdapter);

        loadDanhSachYeuThich();
//        adapter.setOnItemClickListener(new RcvCateFlightAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(ChuyenBay chuyenBay) {
//                // Xử lý khi người dùng click vào mục
//                Intent intent = new Intent(DanhSachChuyenBayDaThich.this, ChiTietFlightActivity.class);
//                intent.putExtra("ThongTinChuyenBay", chuyenBay);
//                startActivity(intent);
//            }
//        });
    }

    private void loadDanhSachYeuThich() {
        // Lấy tham chiếu đến Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Lấy tham chiếu đến bảng dữ liệu chứa danh sách yêu thích
        CollectionReference yeuThichCollection = db.collection("YeuThich");

        // Truy vấn danh sách chuyến bay yêu thích
        yeuThichCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                danhSachYeuThich.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    ChuyenBay chuyenBay = document.toObject(ChuyenBay.class);
                    danhSachYeuThich.add(chuyenBay);
                }
                danhSachYeuThichAdapter.notifyDataSetChanged();
            }
        });
}
    }