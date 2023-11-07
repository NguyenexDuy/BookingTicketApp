    package com.example.cnpmnc.activity;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.Intent;
    import android.os.Bundle;

    import com.example.cnpmnc.R;
    import com.example.cnpmnc.adapter.RcvCateFlightAdapter;
    import com.example.cnpmnc.model.ChuyenBay;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.CollectionReference;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;

    import java.util.ArrayList;

    public class DanhSachChuyenBayDaThich extends AppCompatActivity {
        private ArrayList<ChuyenBay> danhSachYeuThich = new ArrayList<>();
        private RecyclerView rvDanhSachYeuThich;
        private RcvCateFlightAdapter danhSachYeuThichAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_danh_sach_chuyen_bay_da_thich);

          rvDanhSachYeuThich = findViewById(R.id.rvDanhSachChuyenBayDaThich);
            GridLayoutManager layoutManager = new GridLayoutManager(this,2);
            rvDanhSachYeuThich.setLayoutManager(layoutManager);

            danhSachYeuThichAdapter = new RcvCateFlightAdapter(danhSachYeuThich,this);
            rvDanhSachYeuThich.setAdapter(danhSachYeuThichAdapter);

            loadDanhSachYeuThich();
        }

        private void loadDanhSachYeuThich() {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            CollectionReference yeuThichCollection = db.collection("favorites");
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