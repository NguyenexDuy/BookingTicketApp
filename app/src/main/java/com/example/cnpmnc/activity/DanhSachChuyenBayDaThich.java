    package com.example.cnpmnc.activity;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.Toast;

    import com.example.cnpmnc.R;
    import com.example.cnpmnc.adapter.ChuyenBayDaThichAdapter;
    import com.example.cnpmnc.adapter.RcvCateFlightAdapter;
    import com.example.cnpmnc.model.ChuyenBay;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.CollectionReference;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FieldPath;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.Query;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;

    import java.util.ArrayList;

    import android.app.AlertDialog;
    import android.content.DialogInterface;


    public class DanhSachChuyenBayDaThich extends AppCompatActivity {
        ArrayList<ChuyenBay> chuyenBays;
        RecyclerView rvDanhSachYeuThich;
        FirebaseFirestore db;
        ChuyenBayDaThichAdapter chuyenBayDaThichAdapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_danh_sach_chuyen_bay_da_thich);
            rvDanhSachYeuThich = findViewById(R.id.rvDanhSachChuyenBayDaThich);
            chuyenBays = new ArrayList<>();
            ChuyenBayDaThich();
            chuyenBayDaThichAdapter = new ChuyenBayDaThichAdapter(this,chuyenBays);
            rvDanhSachYeuThich.setAdapter(chuyenBayDaThichAdapter);
            LinearLayoutManager layoutManager = new GridLayoutManager(this,2);
            rvDanhSachYeuThich.setLayoutManager(layoutManager);
        }
        private void ChuyenBayDaThich()
        {
            db=FirebaseFirestore.getInstance();
            db.collection("favorites").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        String IdChuyenBayNe = documentSnapshot.getId();
                        String DiemDen = (String) documentSnapshot.get("DiemDen");
                        String DiemDi = (String) documentSnapshot.get("DiemDi");
                        String GioBatDau = (String) documentSnapshot.get("GioBatDau");
                        String HinhAnh = (String) documentSnapshot.get("HinhAnh");
                        String NgayDi = (String) documentSnapshot.get("NgayDi");
                        String NgayVe = (String) documentSnapshot.get("NgayVe");
                        String SoLuongGheTrong = (String) documentSnapshot.get("SoLuongGheTrong");
                        String SoLuongGheVipTrong = (String) documentSnapshot.get("SoLuongGheVipTrong");
                        String TrangThai = (String) documentSnapshot.get("TrangThai");
                        String MoTa  = (String) documentSnapshot.get("MoTa");
                        String MoTaDiemDap = (String) documentSnapshot.get("MoTaDiemDap");
                        Boolean isYeuThich = (Boolean) documentSnapshot.get("isYeuThich");
                        ChuyenBay chuyenBay = new ChuyenBay(IdChuyenBayNe,DiemDen,DiemDi,GioBatDau,HinhAnh,NgayDi,NgayVe,SoLuongGheTrong,SoLuongGheVipTrong,TrangThai,MoTa,MoTaDiemDap,isYeuThich);
                        chuyenBays.add(chuyenBay);
                    }
                    chuyenBayDaThichAdapter.notifyDataSetChanged();
                }
            });
        }
    }