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
import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.ChuyenBayAdapter;
import com.example.cnpmnc.adapter.ChuyenBayLoaiThuongGiaAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.Firebase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoaiVeThuongGiaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoaiVeThuongGiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoaiVeThuongGiaFragment.
     */
    // TODO: Rename and change types and number of parameters




    private RecyclerView rvVeThuongGia;
    private ChuyenBayLoaiThuongGiaAdapter chuyenBayLoaiThuongGiaAdapter;

    private String diemDi;
    private String diemDen;
    private String NgayDi,NgayVe,SoLuongGheVipTrong,SoLuongGheTrong;
    private Firebase mfirebase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_ve_thuong_gia, container, false);
        Anhxa( view);
        diemDi = DiaDiem.getInstance().getDiemDi();
        diemDen = DiaDiem.getInstance().getDiemDen();
        NgayDi = DiaDiem.getInstance().getNgayDi();
        NgayVe=DiaDiem.getInstance().getNgayVe();
        SoLuongGheTrong = DiaDiem.getInstance().getSoLuongGheTrong();
        SoLuongGheVipTrong = DiaDiem.getInstance().getSoLuongGheVipTrong();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvVeThuongGia.setLayoutManager(layoutManager);
        if(NgayVe!=null)
        {
            mfirebase.getAllFlighttoCompareKhuHoi(diemDi, diemDen, NgayDi, NgayVe, new Firebase.FirebaseCallback<ChuyenBay>() {
                @Override
                public void onCallback(ArrayList<ChuyenBay> list) {
                    chuyenBayLoaiThuongGiaAdapter = new ChuyenBayLoaiThuongGiaAdapter(list, getContext());
                    rvVeThuongGia.setAdapter(chuyenBayLoaiThuongGiaAdapter);
                }
            });
        }else {
            mfirebase.getAllFlighttoCompare(diemDi, diemDen, NgayDi, new Firebase.FirebaseCallback<ChuyenBay>() {
                @Override
                public void onCallback(ArrayList<ChuyenBay> list) {
                    chuyenBayLoaiThuongGiaAdapter = new ChuyenBayLoaiThuongGiaAdapter(list, getContext());
                    rvVeThuongGia.setAdapter(chuyenBayLoaiThuongGiaAdapter);
                }
            });
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvVeThuongGia.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
    private void Anhxa(View view) {
        mfirebase = new Firebase(getContext());
        rvVeThuongGia = view.findViewById(R.id.rvVeThuongGia);
    }

}