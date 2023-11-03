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
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.ChuyenBayAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.Firebase;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiVePhoThongFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiVePhoThongFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoaiVePhoThongFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoaiVePhoThongFragment.
     */
    // TODO: Rename and change types and number of parameters

    private RecyclerView rvVePhoThong;
    private ChuyenBayAdapter chuyenBayAdapter;
    private ChuyenBay chuyenBay;
    public static LoaiVePhoThongFragment newInstance(String param1, String param2) {
        LoaiVePhoThongFragment fragment = new LoaiVePhoThongFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance(ChuyenBay chuyenBay) {
        LoaiVePhoThongFragment fragment = new LoaiVePhoThongFragment();
        fragment.chuyenBay=chuyenBay;
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private Firebase mfirebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_ve_pho_thong, container, false);
        Anhxa( view);

       if(chuyenBay!=null)
       {
           Toast.makeText(getContext(), "Dit det cute dang yeu", Toast.LENGTH_SHORT).show();

           mfirebase.getAllFlighttoCompare(chuyenBay.getDiemDi(), chuyenBay.getDiemDen(), new Firebase.FirebaseCallback<ChuyenBay>() {
               @Override
               public void onCallback(ArrayList<ChuyenBay> list) {
                   chuyenBayAdapter=new ChuyenBayAdapter(list,getContext());
                   rvVePhoThong.setLayoutManager(new LinearLayoutManager(getActivity()));
                   rvVePhoThong.setAdapter(chuyenBayAdapter);
               }
           });
       } else if (DiaDiem.getInstance().getDiemDi() != null || DiaDiem.getInstance().getDiemDen() != null) {
           Toast.makeText(getContext(), "Dit cho de", Toast.LENGTH_SHORT).show();
           
       } else {
           Toast.makeText(getContext(), "Dau chua cut", Toast.LENGTH_SHORT).show();
       }
        return view;
    }

    private void Anhxa(View view) {
        mfirebase = new Firebase(getContext());
        rvVePhoThong = view.findViewById(R.id.rvVePhoThong);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}