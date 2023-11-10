package com.example.cnpmnc.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.DangNhapActivity;
import com.example.cnpmnc.activity.DanhSachChuyenBayDaThich;
import com.example.cnpmnc.activity.FormPersonActivity;
import com.example.cnpmnc.activity.LichSuDatVeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
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


    TextView tvten,tvemail,tvdangxuat,tvttcn,tvLichSuDatVe,tvChuyenBayYeuThich;

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvttcn=view.findViewById(R.id.tvinfo);
        tvLichSuDatVe = view.findViewById(R.id.tvLicSuDatVe);
        tvChuyenBayYeuThich = view.findViewById(R.id.tvChuyenBayDaThich);
        tvten=view.findViewById(R.id.tvten);
        tvemail=view.findViewById(R.id.tvemail);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        tvdangxuat=view.findViewById(R.id.tvDangXuat);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            String userUid = firebaseUser.getUid();
            DocumentReference nguoiDungDocRef = db.collection("Customer").document(userUid);

            nguoiDungDocRef.get().addOnSuccessListener(nguoiDungDocumentSnapshot -> {
                if (nguoiDungDocumentSnapshot.exists()) {
                    String Gmail = nguoiDungDocumentSnapshot.getString("Gmail");
                    DocumentReference khachHangDocRef = db.collection("KhachHang").document(userUid);

                    khachHangDocRef.get().addOnSuccessListener(khachHangDocumentSnapshot -> {
                        if (khachHangDocumentSnapshot.exists()) {
                            String hoVaTen = khachHangDocumentSnapshot.getString("HoTen");
                            tvten.setText(hoVaTen);
                        } else {
                            tvten.setText("");
                        }
                    }).addOnFailureListener(e -> {
                        tvten.setText("Bạn chưa chỉnh sửa TTCN");
                    });

                    // Set the email to the email TextView
                    tvemail.setText(Gmail);
                } else {
                    tvemail.setText("Email không tồn tại");
                    tvten.setText("Họ Tên Không tồn tại");
                }
            }).addOnFailureListener(e -> {
                tvemail.setText("Lỗi khi lấy dữ liệu từ Firestore");
                tvten.setText("Lỗi khi lấy dữ liệu từ Firestore");
            });
        } else {

            tvemail.setText("Chưa đăng nhập");
            tvten.setText("Chưa đăng nhập");
        }
        tvdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                signOutUser();
            }
        });
        tvttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FormPersonActivity.class));
            }
        });
        tvLichSuDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LichSuDatVeActivity.class));
            }
        });
        tvChuyenBayYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Toast.makeText(getContext(), userID, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), DanhSachChuyenBayDaThich.class));
            }
        });
        if(tvemail.getText().toString().equals("Chưa đăng nhập")&&tvten.getText().toString().equals("Chưa đăng nhập")){
            tvdangxuat.setText("Đăng xuất");
            tvttcn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), DangNhapActivity.class));

                }
            });
        }
    }
    private void signOutUser() {
        Intent intent=new Intent(getContext(), DangNhapActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}