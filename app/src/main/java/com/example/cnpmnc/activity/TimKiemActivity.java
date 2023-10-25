package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.TimKiemFlightAdapter;
import com.example.cnpmnc.model.Firebase;
import com.example.cnpmnc.model.SanBay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TimKiemActivity extends AppCompatActivity {

    ArrayList<SanBay> sanBays;
    RecyclerView rcv_nameitemFlight;
    TimKiemFlightAdapter timKiemFlightAdapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        rcv_nameitemFlight=findViewById(R.id.rcv_nameitemFlight);
        sanBays=new ArrayList<>();
        timKiemFlightAdapter=new TimKiemFlightAdapter(TimKiemActivity.this,sanBays);
        rcv_nameitemFlight.setAdapter(timKiemFlightAdapter);
        rcv_nameitemFlight.setLayoutManager(new LinearLayoutManager(TimKiemActivity.this,LinearLayoutManager.VERTICAL,false));
        db=FirebaseFirestore.getInstance();
        db.collection("SanBay").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                {
                    String IdSanBay=documentSnapshot.getId();
                    String tenSanBay= (String) documentSnapshot.get("TenSanBay");
                    SanBay sanBay=new SanBay(IdSanBay, tenSanBay);
                    sanBays.add(sanBay);
                }


            }
        });

    }
}