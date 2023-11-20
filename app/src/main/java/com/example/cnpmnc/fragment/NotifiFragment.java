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
import com.example.cnpmnc.adapter.NotifiCationAdapter;
import com.example.cnpmnc.model.VeMayBay;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotifiFragment() {
        // Required empty public constructor
    }


    public static NotifiFragment newInstance(String param1, String param2) {
        NotifiFragment fragment = new NotifiFragment();
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

    RecyclerView rcv_notifi;
    ArrayList<VeMayBay> veMayBays;
    NotifiCationAdapter notifiCationAdapter;
    FirebaseFirestore firebaseFirestore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_notifi=view.findViewById(R.id.rcv_notifi);
        veMayBays= new ArrayList<>();
        firebaseFirestore=FirebaseFirestore.getInstance();
        String userId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        firebaseFirestore.collection("VeMayBay").whereEqualTo("KhachHangID",userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    String idVe=documentSnapshot.getId();
                    String idHangKhach= (String) documentSnapshot.get("KhachHangID");
                    String idChuyenBay= (String) documentSnapshot.get("ChuyenBayID");
                    String diemDi= (String) documentSnapshot.get("diemDi");
                    String diemDen=(String) documentSnapshot.get("diemDen");
                    String ngayBatDau=(String) documentSnapshot.get("ngayBatDau");
                    String giaVe=(String) documentSnapshot.get("giaVe");
                    VeMayBay veMayBay=new VeMayBay(idVe,idChuyenBay,idHangKhach,diemDi,diemDen,ngayBatDau,giaVe);
                    veMayBays.add(veMayBay);
                }
                notifiCationAdapter.notifyDataSetChanged();
            }
        });
        notifiCationAdapter=new NotifiCationAdapter(getContext(),veMayBays);
        rcv_notifi.setAdapter(notifiCationAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_notifi.setLayoutManager(layoutManager);

    }
}