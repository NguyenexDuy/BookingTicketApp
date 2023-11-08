package com.example.cnpmnc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.Adapterviewpager;
import com.example.cnpmnc.adapter.RcvCateFlightAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    int currentPage = 0;
    private ViewPager viewPager;
    private RecyclerView rcv_flightFromTPHCM, rcv_flightFromHN, rcv_flightFromDaNang;
    private RcvCateFlightAdapter rcvCateFlightAdapter1,rcvCateFlightAdapter2, rcvCateFlightAdapter3;
    private ArrayList<ChuyenBay> flightlist1, flightlist2, flightlist3;
    private Firebase firebase;

    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        Anhxa(view);
        setAutoScrollViewScroll();
        setDataForRcv();
        return view;
    }
    private void Anhxa(View view){
        firebase = new Firebase(getContext());
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rcv_flightFromTPHCM = (RecyclerView) view.findViewById(R.id.rcv_flightFromTPHCM);
        rcv_flightFromHN = (RecyclerView) view.findViewById(R.id.rcv_flightFromHN);
        rcv_flightFromDaNang = (RecyclerView) view.findViewById(R.id.rcv_flightFromDaNang);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBarid);

    }
    private void setDataForRcv(){
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_flightFromTPHCM.setLayoutManager(layoutManager1);
        rcv_flightFromHN.setLayoutManager(layoutManager2);
        rcv_flightFromDaNang.setLayoutManager(layoutManager3);
        firebase.getAllFlightByDiemDi("Tp. Hồ Chí Minh", new Firebase.FirebaseCallback<ChuyenBay>() {
            @Override
            public void onCallback(ArrayList<ChuyenBay> list) {
                flightlist1 = list;
                rcvCateFlightAdapter1 = new RcvCateFlightAdapter(getContext(), flightlist1, firebase);
                rcv_flightFromTPHCM.setAdapter(rcvCateFlightAdapter1);
                progressBar.setVisibility(View.GONE);
            }
        });
        firebase.getAllFlightByDiemDi("Hà Nội", new Firebase.FirebaseCallback<ChuyenBay>() {
            @Override
            public void onCallback(ArrayList<ChuyenBay> list2) {
                flightlist2 = list2;
                rcvCateFlightAdapter2 = new RcvCateFlightAdapter(getContext(), flightlist2, firebase);
                rcv_flightFromHN.setAdapter(rcvCateFlightAdapter2);
            }
        });
        firebase.getAllFlightByDiemDi("Đà Nẵng", new Firebase.FirebaseCallback<ChuyenBay>() {
            @Override
            public void onCallback(ArrayList<ChuyenBay> list3) {
                flightlist3 = list3;
                rcvCateFlightAdapter3 = new RcvCateFlightAdapter(getContext(), flightlist3, firebase);
                rcv_flightFromDaNang.setAdapter(rcvCateFlightAdapter3);
            }
        });
    }
    private void setAutoScrollViewScroll() {
        Adapterviewpager adapterviewpager = new Adapterviewpager(getContext());
        viewPager.setAdapter(adapterviewpager);
        viewPager.setClipToOutline(true);
        Timer timer;
        final long DELAY_MS = 500;
        final long PERIOD_MS = 2000;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) currentPage = 0;
                viewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },DELAY_MS, PERIOD_MS);
    }
}