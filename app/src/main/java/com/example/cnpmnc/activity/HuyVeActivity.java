package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.HuyVeAdapter;
import com.example.cnpmnc.model.VeMayBay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HuyVeActivity extends AppCompatActivity {

    ArrayList<VeMayBay> veMayBays;
    HuyVeAdapter huyVeAdapter;
    RecyclerView rcv_huyVe;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huy_ve);
        rcv_huyVe=findViewById(R.id.rcv_huyVe);
        veMayBays=new ArrayList<>();
        Notification2();

        huyVeAdapter=new HuyVeAdapter(HuyVeActivity.this,veMayBays);
        rcv_huyVe.setAdapter(huyVeAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(HuyVeActivity.this, LinearLayoutManager.VERTICAL, false);
        rcv_huyVe.setLayoutManager(layoutManager);

    }
    private void Notification2() {
        FirebaseUser current = FirebaseAuth.getInstance().getCurrentUser();
        if (current != null) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            firebaseFirestore = FirebaseFirestore.getInstance();
            firebaseFirestore.collection("VeMayBay").whereEqualTo("KhachHangID", userId).whereEqualTo("TrangThaiVe",true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        String idVe = documentSnapshot.getId();
                        String idHangKhach = (String) documentSnapshot.get("KhachHangID");
                        String idChuyenBay = (String) documentSnapshot.get("ChuyenBayID");
                        String diemDi = (String) documentSnapshot.get("diemDi");
                        String diemDen = (String) documentSnapshot.get("diemDen");
                        String gioDi=(String) documentSnapshot.get("gioDi");
                        String gioVe=(String) documentSnapshot.get("gioVe");
                        String ngayBatDau = (String) documentSnapshot.get("ngayBatDau");
                        String ngayVe=(String) documentSnapshot.get("ngayVe");
                        String giaVe = (String) documentSnapshot.get("giaVe");
                        Boolean canceled=(Boolean) documentSnapshot.get("TrangThaiVe");
                        ArrayList<Map<String, Object>> hangKhachList = new ArrayList<>();
                        int index = 0;
                        while (documentSnapshot.contains("hangKhach_" + index)) {
                            Map<String, Object> hangKhachMap = (Map<String, Object>) documentSnapshot.get("hangKhach_" + index);
                            if (hangKhachMap != null) {
                                hangKhachList.add(hangKhachMap);
                            }
                            index++;
                        }
                        VeMayBay veMayBay = new VeMayBay(idVe, idChuyenBay, idHangKhach, diemDi, diemDen,gioDi,gioVe, ngayBatDau,ngayVe, giaVe,canceled, hangKhachList);
                        veMayBays.add(veMayBay);
                    }
                    huyVeAdapter.notifyDataSetChanged();
                }

            });
            Log.d("HuyVeActivity", "Số lượng vé: " + veMayBays.size());
        }
    }

}