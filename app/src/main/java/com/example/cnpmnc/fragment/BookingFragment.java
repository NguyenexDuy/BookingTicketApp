package com.example.cnpmnc.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChonChuyenBayActivity;
import com.example.cnpmnc.adapter.VPBookingAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ChuyenBay chuyenBay;
    private String DiemDi, DiemDen;
    public BookingFragment() {
        // Required empty public constructor
    }
    public BookingFragment(ChuyenBay chuyenbay) {
        this.chuyenBay = chuyenbay;
    }
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    private Button btnTimKiem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        Anhxa(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (chuyenBay != null){
            VPBookingAdapter viewPagerAdapter = new VPBookingAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, chuyenBay);
            mviewPager.setAdapter(viewPagerAdapter);
            mtabLayout.setupWithViewPager(mviewPager);
        }
        else {
            VPBookingAdapter viewPagerAdapter = new VPBookingAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            mviewPager.setAdapter(viewPagerAdapter);
            mtabLayout.setupWithViewPager(mviewPager);
        }
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ChonChuyenBayActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa(View view) {
        mtabLayout = view.findViewById(R.id.tab_layout);
        mviewPager = view.findViewById(R.id.view_pager);
        btnTimKiem=view.findViewById(R.id.btnTimKiem);
    }
}