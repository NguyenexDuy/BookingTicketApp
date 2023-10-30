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
import android.widget.ImageButton;
import android.widget.TextView;
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
    private ImageButton btn_minus1,btn_plus1,btn_minus2,btn_plus2,btn_minus3,btn_plus3;
    private TextView tv_countNguoiLon,tv_count2NguoiLon,tv_count3NguoiLon;
    int countNguoiLon=0;
    int countTreEm2_12Tuoi=0;
    int countTreEmDuoi2tuoi=0;
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
        Action();
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
    private  void Action()
    {
        btn_minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countNguoiLon>0)
                {
                    countNguoiLon--;
                    updateCount(tv_countNguoiLon,countNguoiLon);
                }

            }
        });
        btn_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEm2_12Tuoi>0)
                {
                    countTreEm2_12Tuoi--;
                    updateCount(tv_count2NguoiLon,countTreEm2_12Tuoi);
                }

            }
        });
        btn_minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi>0)
                {
                    countTreEmDuoi2tuoi--;
                    updateCount(tv_count3NguoiLon,countTreEmDuoi2tuoi);
                }

            }
        });
        btn_plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countNguoiLon++;
                updateCount(tv_countNguoiLon,countNguoiLon);
            }
        });
        btn_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEm2_12Tuoi++;
                updateCount(tv_count2NguoiLon,countTreEm2_12Tuoi);
            }
        });
        btn_plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEmDuoi2tuoi++;
                updateCount(tv_count3NguoiLon,countTreEmDuoi2tuoi);
            }
        });
    }
    private void updateCount(TextView text,int count) {
        text.setText(String.format("%02d", count));
    }

    private void Anhxa(View view) {
        mtabLayout = view.findViewById(R.id.tab_layout);
        mviewPager = view.findViewById(R.id.view_pager);
        btnTimKiem=view.findViewById(R.id.btnTimKiem);
        btn_minus1=view.findViewById(R.id.btn_minus);
        btn_minus2=view.findViewById(R.id.btn_minus2);
        btn_minus3=view.findViewById(R.id.btn_minus3);
        btn_plus1=view.findViewById(R.id.btn_plus);
        btn_plus2=view.findViewById(R.id.btn_plus2);
        btn_plus3=view.findViewById(R.id.btn_plus3);
        tv_countNguoiLon=view.findViewById(R.id.tv_countNguoiLon);
        tv_count2NguoiLon=view.findViewById(R.id.tv_count2NguoiLon);
        tv_count3NguoiLon=view.findViewById(R.id.tv_count3NguoiLon);
    }
}