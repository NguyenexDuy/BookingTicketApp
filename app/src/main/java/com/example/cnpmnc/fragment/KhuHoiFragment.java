package com.example.cnpmnc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhuHoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhuHoiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ChuyenBay chuyenBay;
    public KhuHoiFragment() {
        // Required empty public constructor
    }
    public KhuHoiFragment(ChuyenBay chuyenbay) {
        this.chuyenBay = chuyenbay;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhuHoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhuHoiFragment newInstance(String param1, String param2) {
        KhuHoiFragment fragment = new KhuHoiFragment();
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
    TextView tv_idsanbaydiemden, tv_tensanbaydiemden, tv_idsanbaydiemdi, tv_tensanbaydiemdi;
    Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khu_hoi, container, false);
        Anhxa(view);
        setdata();
        return view;
    }
    private void setdata(){
        if (chuyenBay != null){
            tv_idsanbaydiemdi.setText(chuyenBay.getDiemDi());
            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDi(), new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemdi.setText(tensanbay);
                }
            });
            tv_idsanbaydiemden.setText(chuyenBay.getDiemDen());
            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDen(), new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemden.setText(tensanbay);
                }
            });
        }
    }
    private void Anhxa(View view){
        firebase = new Firebase(getContext());
        tv_idsanbaydiemdi = view.findViewById(R.id.tv_idsanbaydiemdi);
        tv_tensanbaydiemdi = view.findViewById(R.id.tv_tensanbaydiemdi);
        tv_idsanbaydiemden = view.findViewById(R.id.tv_idsanbaydiemden);
        tv_tensanbaydiemden = view.findViewById(R.id.tv_tensanbaydiemden);
    }
}