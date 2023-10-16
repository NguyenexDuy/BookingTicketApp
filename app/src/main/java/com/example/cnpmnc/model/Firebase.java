package com.example.cnpmnc.model;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

//file cua anh Nhan,Duy dung vo chem/ ak.
public class Firebase {
    FirebaseFirestore mfirestore;
    FirebaseAuth mfirebaseAuth;
    FirebaseUser mfirebaseUser;
    FirebaseStorage mfirebaseStorage;
    StorageReference mstorageRef;
    Context mcontext;
    public Firebase(Context context) {
        mfirestore = FirebaseFirestore.getInstance();
        mfirebaseAuth = FirebaseAuth.getInstance();
        mfirebaseStorage = FirebaseStorage.getInstance();
        mstorageRef = mfirebaseStorage.getReference();
        this.mcontext = context;
    }
    public interface FirebaseCallback<T> {
        void onCallback(ArrayList<T> list);
    }
    public interface getTenSanBayBySanBayIdCallback {
        void onCallback(String tensanbay);
    }
    public void getAllFlightByDiemDi(String diemdi,FirebaseCallback<ChuyenBay> callback) {
        ArrayList<ChuyenBay> flightlist = new ArrayList<>();
        mfirestore.collection("ChuyenBay")
                .whereEqualTo("DiemDi", diemdi)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            ChuyenBay chuyenBay = new ChuyenBay(document.getId(),
                                    document.getString("DiemDen"),
                                    document.getString("DiemDi"),
                                    document.getString("GioBatDau"),
                                    document.getString("HinhAnh"),
                                    document.getString("NgayDi"),
                                    document.getString("NgayVe"),
                                    document.getString("SoLuongGheTrong"),
                                    document.getString("SoLuongGheVipTrong"),
                                    document.getString("TrangThai"));
                            flightlist.add(chuyenBay);
                        }
                        callback.onCallback(flightlist);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
    public void getTenSanBayBySanBayId(String sanBayId, getTenSanBayBySanBayIdCallback callback) {
        mfirestore.collection("SanBay")
                .document(sanBayId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String tenSanBay = document.getString("TenSanBay");
                            callback.onCallback(tenSanBay);
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.w(TAG, "Error getting document", task.getException());
                    }
                });
    }
}
