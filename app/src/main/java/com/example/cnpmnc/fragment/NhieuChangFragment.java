package com.example.cnpmnc.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChonChoNgoiActivity;
import com.example.cnpmnc.adapter.NhieuChangAdapter;
import com.example.cnpmnc.model.ChuyenBay;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NhieuChangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NhieuChangFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NhieuChangFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NhieuChangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NhieuChangFragment newInstance(String param1, String param2) {
        NhieuChangFragment fragment = new NhieuChangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView rcv_nhieuChang;
    LinearLayout btn_themChuyenBay;
    NhieuChangAdapter nhieuChangAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_themChuyenBay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewItemToRecyclerView();
            }
        });


    }

    private void addNewItemToRecyclerView() {
        ChuyenBay newItem = new ChuyenBay();

        nhieuChangAdapter.addItem(newItem);

        nhieuChangAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhieu_chang, container, false);
        Anhxa(view);

        ArrayList<ChuyenBay> chuyenBays = new ArrayList<>();
        ChuyenBay chuyenBay = new ChuyenBay();
        ChuyenBay chuyenBay2 = new ChuyenBay();
        chuyenBays.add(chuyenBay);
        chuyenBays.add(chuyenBay2);

        nhieuChangAdapter = new NhieuChangAdapter(chuyenBays, getContext());
        rcv_nhieuChang.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv_nhieuChang.setAdapter(nhieuChangAdapter);

        return view;
    }

    private void Anhxa(View view) {
        btn_themChuyenBay=view.findViewById(R.id.btn_themChuyenBay);
        rcv_nhieuChang = view.findViewById(R.id.rcv_nhieuChang);
    }

}