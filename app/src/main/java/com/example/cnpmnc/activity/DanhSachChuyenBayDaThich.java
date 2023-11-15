    package com.example.cnpmnc.activity;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.GridLayoutManager;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.widget.Toast;

    import com.example.cnpmnc.R;
    import com.example.cnpmnc.adapter.ChuyenBayDaThichAdapter;
    import com.example.cnpmnc.adapter.RcvCateFlightAdapter;
    import com.example.cnpmnc.model.ChuyenBay;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.firestore.CollectionReference;
    import com.google.firebase.firestore.DocumentReference;
    import com.google.firebase.firestore.FieldPath;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.Query;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;

    import java.util.ArrayList;

    import android.app.AlertDialog;
    import android.content.DialogInterface;


    public class DanhSachChuyenBayDaThich extends AppCompatActivity {
        private ArrayList<ChuyenBay> danhSachYeuThich = new ArrayList<>();
        private RecyclerView rvDanhSachYeuThich;
        private ChuyenBayDaThichAdapter chuyenBayDaThichAdapter;
//        private RcvCateFlightAdapter danhSachYeuThichAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_danh_sach_chuyen_bay_da_thich);

          rvDanhSachYeuThich = findViewById(R.id.rvDanhSachChuyenBayDaThich);
            GridLayoutManager layoutManager = new GridLayoutManager(this,2);
            rvDanhSachYeuThich.setLayoutManager(layoutManager);

            chuyenBayDaThichAdapter = new ChuyenBayDaThichAdapter(danhSachYeuThich,this);
            rvDanhSachYeuThich.setAdapter(chuyenBayDaThichAdapter);

            loadDanhSachYeuThich();
            chuyenBayDaThichAdapter.setOnItemLongClickListener(new ChuyenBayDaThichAdapter.OnItemLongClickListener() {
                @Override
                public void onItemLongClick(ChuyenBay chuyenBay) {
                    // Hiển thị thông báo xác nhận xóa ở đây
                    showConfirmationDialog(chuyenBay);
                }
            });
        }

        private void loadDanhSachYeuThich() {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            CollectionReference yeuThichCollection = db.collection("favorites");
            Query query=yeuThichCollection.whereEqualTo("userID",userID);
            query.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    danhSachYeuThich.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ChuyenBay chuyenBay = document.toObject(ChuyenBay.class);
                        danhSachYeuThich.add(chuyenBay);
                    }
                    chuyenBayDaThichAdapter.notifyDataSetChanged();
                }
            });
    }
        private void showConfirmationDialog(ChuyenBay chuyenBay) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có muốn xóa khỏi chuyến bay yêu thích ?");
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                   deleteItem(chuyenBay);
//                    xoaDulieuTrenFirestore(chuyenBay);
                    deleteItemFromFirestore(chuyenBay);

                }
            });

            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        private void deleteItem(ChuyenBay chuyenBay) {
                int position = danhSachYeuThich.indexOf(chuyenBay);
                if (position != -1) {
                    danhSachYeuThich.remove(position);
                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    String itemID = chuyenBay.getIdChuyenBay();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference yeuThichCollection = db.collection("favorites");
                    DocumentReference documentReference = yeuThichCollection.document(userID + "_" + itemID);
                    documentReference.delete()
                            .addOnSuccessListener(aVoid -> {
                                chuyenBayDaThichAdapter.notifyItemRemoved(position);
                                Toast.makeText(this, "Xóa thành công !!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this, "Xóa không thành công !!", Toast.LENGTH_SHORT).show();
                                Log.e("Firestore", "Error deleting document: " + e.getMessage(), e);
                            });
                }
            }
        private void xoaDulieuTrenFirestore(ChuyenBay chuyenBay) {
            int position = danhSachYeuThich.indexOf(chuyenBay);
            if (position != -1) {
                danhSachYeuThich.remove(position);
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Check if the ID is not null before proceeding
                String documentID = chuyenBay.getIdChuyenBay();
                Log.d("Firestore", "Document ID: " + documentID);
                if (documentID != null) {
                    CollectionReference yeuThichCollection = db.collection("favorites");
                    yeuThichCollection
                            .document(documentID)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(DanhSachChuyenBayDaThich.this, "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(DanhSachChuyenBayDaThich.this, "Lỗi khi xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    // Handle the case where the ID is null
                    Toast.makeText(DanhSachChuyenBayDaThich.this, "ID is null", Toast.LENGTH_SHORT).show();
                }
            }
        }
        private void deleteItemFromFirestore(ChuyenBay chuyenBay) {

            String itemID = chuyenBay.getIdChuyenBay();
            if (itemID != null) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference yeuThichCollection = db.collection("favorites");

                yeuThichCollection.document(itemID)
                        .delete()
                        .addOnSuccessListener(aVoid -> {
                            // Item deleted successfully from Firestore
                            // Now, update the local list and notify the adapter
                            int position = danhSachYeuThich.indexOf(chuyenBay);
                            if (position != -1) {
                                danhSachYeuThich.remove(position);
                                chuyenBayDaThichAdapter.notifyItemRemoved(position);
                                Toast.makeText(this, "Xóa thành công từ Firestore!!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            // Handle failure to delete item from Firestore
                            Toast.makeText(this, "Xóa không thành công từ Firestore!!", Toast.LENGTH_SHORT).show();
                            Log.e("Firestore", "Error deleting document: " + e.getMessage(), e);
                        });
            } else {
                // Handle the case where the document ID is null
                Toast.makeText(this, "Document ID is null", Toast.LENGTH_SHORT).show();
            }
        }



    }