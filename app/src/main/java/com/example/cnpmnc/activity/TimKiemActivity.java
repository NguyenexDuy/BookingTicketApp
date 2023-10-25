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

    private RecyclerView rcv_nameitemFlight;
    private ArrayList<SanBay> sanBaysList;
    private TimKiemFlightAdapter timKiemFlightAdapter;
    private Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        Anhxa();
        firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
            @Override
            public void onCallback(ArrayList<SanBay> list) {
                sanBaysList = list;
                timKiemFlightAdapter=new TimKiemFlightAdapter(TimKiemActivity.this,sanBaysList);
                rcv_nameitemFlight.setAdapter(timKiemFlightAdapter);
            }
        });
    }
    private void Anhxa() {
        firebase = new Firebase(TimKiemActivity.this);
        rcv_nameitemFlight=findViewById(R.id.rcv_nameitemFlight);
        rcv_nameitemFlight.setLayoutManager(new LinearLayoutManager(this));

    }
}