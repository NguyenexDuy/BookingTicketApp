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
    private ChuyenBay chuyenBay;
    private String key;
    private String key1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        Anhxa();
        key = getIntent().getStringExtra("Timkiem");
        key1=getIntent().getStringExtra("Timkiem1");
        if (key != null && key.equals("diemdi")){
            checkdiemdi();
        }else if(key1 != null && key1.equals("diemden")) {
            checkdiemden();
        }
        checkdiemdi();
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
    private void checkdiemden() {
        if(getIntent().getSerializableExtra("Chuyenbay1")!=null)
        {
            chuyenBay=(ChuyenBay) getIntent().getSerializableExtra("ChuyenBay1");
            firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
                @Override
                public void onCallback(ArrayList<SanBay> list) {
                    sanBaysList=list;
                    timKiemDiemDenAdapter=new TimKiemDiemDenAdapter(TimKiemActivity.this,sanBaysList,chuyenBay);
                    rcv_nameitemFlight.setAdapter(timKiemDiemDenAdapter);
                }
            });
        }
        else {
            firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
                @Override
                public void onCallback(ArrayList<SanBay> list) {
                    sanBaysList=list;
                    timKiemDiemDenAdapter=new TimKiemDiemDenAdapter(TimKiemActivity.this,sanBaysList,chuyenBay);
                    rcv_nameitemFlight.setAdapter(timKiemDiemDenAdapter);
                }
            });
        }
    }
    private  void checkdiemdi(){
        if (getIntent().getSerializableExtra("Chuyenbay") != null){
            chuyenBay = (ChuyenBay) getIntent().getSerializableExtra("Chuyenbay");
            firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
                @Override
                public void onCallback(ArrayList<SanBay> list) {
                    sanBaysList = list;
                    timKiemFlightAdapter=new TimKiemDiemDiAdapter(TimKiemActivity.this,sanBaysList,chuyenBay);
                    rcv_nameitemFlight.setAdapter(timKiemFlightAdapter);
                }
            });
        }else {
            firebase.getAllSanBay(new Firebase.FirebaseCallback<SanBay>() {
                @Override
                public void onCallback(ArrayList<SanBay> list) {
                    sanBaysList = list;
                    timKiemFlightAdapter=new TimKiemDiemDiAdapter(TimKiemActivity.this,sanBaysList);
                    rcv_nameitemFlight.setAdapter(timKiemFlightAdapter);
                }
            });
        }

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