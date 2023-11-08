package com.example.cnpmnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.TimKiemDiemDenAdapter;
import com.example.cnpmnc.adapter.TimKiemDiemDiAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;
import com.example.cnpmnc.model.SanBay;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    private RecyclerView rcv_nameitemFlight;
    private ArrayList<SanBay> sanBaysList;
    private  SearchView searchView;
    private TimKiemDiemDiAdapter timKiemFlightAdapter;
    private TimKiemDiemDenAdapter timKiemDiemDenAdapter;
    private Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        Anhxa();
        String key = getIntent().getStringExtra("Timkiem");
        check(key);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<SanBay> filteredList = filter(sanBaysList, newText);
                timKiemFlightAdapter.setData(filteredList);
                timKiemDiemDenAdapter.setData(filteredList);
                timKiemDiemDenAdapter.notifyDataSetChanged();
                timKiemFlightAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }



    private void Anhxa() {
        firebase = new Firebase(TimKiemActivity.this);
        rcv_nameitemFlight=findViewById(R.id.rcv_nameitemFlight);
        rcv_nameitemFlight.setLayoutManager(new LinearLayoutManager(this));
        searchView = findViewById(R.id.searchView);
    }
    private  void check(String key){
        firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
            @Override
            public void onCallback(ArrayList<SanBay> list) {
                sanBaysList = list;
                timKiemFlightAdapter=new TimKiemDiemDiAdapter(TimKiemActivity.this,sanBaysList, key);
                rcv_nameitemFlight.setAdapter(timKiemFlightAdapter);
            }
        });
    }
    private ArrayList<SanBay> filter(List<SanBay> sanBays, String query) {
        query = query.toLowerCase().trim();

        final ArrayList<SanBay> filteredList = new ArrayList<>();
        for (SanBay sanBay : sanBays) {
            if (sanBay.getTenSanBay().toLowerCase().contains(query)) {
                filteredList.add(sanBay);
            }
        }
        return filteredList;
    }
}