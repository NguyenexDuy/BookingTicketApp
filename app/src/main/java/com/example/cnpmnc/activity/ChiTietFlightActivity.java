package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ChiTietFlightActivity extends AppCompatActivity {

    private ImageView img_chiTietFight,imgcc;
    private ChuyenBay chuyenBay;
    private FirebaseFirestore db;
    private Button btn_datve;
    private CheckBox tymButton;
    private boolean isFavorite = false;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String IS_YEU_THICH = "isYeuThich_";
    private static final String CHECKBOX_BACKGROUND = "checkBoxBackground_";
    TextView tv_chiTietDiemDen,tv_MoTaChiTietFight,tv_DiemDapChiTiet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_flight);

        Anhxa();
            chuyenBay=(ChuyenBay)getIntent().getSerializableExtra("ThongTinChuyenBay");

        Glide.with(ChiTietFlightActivity.this).load(chuyenBay.getHinhAnh()).into(img_chiTietFight);
        db=FirebaseFirestore.getInstance();

        String tv_chitietDiemDen=chuyenBay.getDiemDen();
        String tv_mota= chuyenBay.getMoTa();
        String tv_diemdapchitiet=chuyenBay.getMoTaDiemDap();
        tv_chiTietDiemDen.setText(tv_chitietDiemDen);
        tv_MoTaChiTietFight.setText(tv_mota);
        tv_DiemDapChiTiet.setText(tv_diemdapchitiet);

        btn_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChiTietFlightActivity.this, HomePageActivity.class);
                intent.putExtra("Chuyenbay", chuyenBay);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isYeuThich = sharedPreferences.getBoolean(IS_YEU_THICH + chuyenBay.getIdChuyenBay(), false);
        int checkBoxBackground = sharedPreferences.getInt(CHECKBOX_BACKGROUND + chuyenBay.getIdChuyenBay(), R.drawable.baseline_favorite_border_24);
        tymButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chuyenBay.setYeuThich(true);
                    Toast.makeText(ChiTietFlightActivity.this, "Thành công cmnr", Toast.LENGTH_SHORT).show();
                    putChuyenBayYeuThich(chuyenBay);
                } else {
                    chuyenBay.setYeuThich(false);
                    xoaDulieuTrenFirestore(chuyenBay);
                    tymButton.setBackgroundResource(R.drawable.heart_24);
                }



            }
        });
    }

    private void Anhxa() {
        btn_datve =findViewById(R.id.btn_datve);
        img_chiTietFight=findViewById(R.id.img_chiTietFight);
        tv_chiTietDiemDen =findViewById(R.id.tv_chiTietDiemDi);
        tv_MoTaChiTietFight=findViewById(R.id.tv_MoTaChiTietFight);
        tv_DiemDapChiTiet=findViewById(R.id.tv_DiemDapChiTiet);
        tymButton = findViewById(R.id.TymButton);
    }
    private void kiemTraTrangThaiYeuThich() {
//         Kiểm tra xem chuyến bay đã trong danh sách yêu thích hay không và cập nhật nút "tym" tương ứng.
        if (chuyenBay.isYeuThich()==true) {
            tymButton.setChecked(chuyenBay.isYeuThich());
            tymButton.setBackgroundResource(R.drawable.heart_red_24);

        } else {
            tymButton.setChecked(false);
            tymButton.setBackgroundResource(R.drawable.baseline_favorite_border_24);
        }

    }
    private void lưuDulieuLenFirestore(ChuyenBay chuyenBay) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference yeuThichCollection = db.collection("YeuThich");

        Map<String, Object> yeuThichData = new HashMap<>();
        yeuThichData.put("DiemDen", chuyenBay.getDiemDen());
        yeuThichData.put("HinhAnh",chuyenBay.getHinhAnh());
        yeuThichData.put("DiemDi", chuyenBay.getDiemDi());
        yeuThichData.put("GioBatDau",chuyenBay.getGioBatDau());
        yeuThichData.put("NgayDi",chuyenBay.getNgayDi());
        yeuThichData.put("NgayVe",chuyenBay.getNgayVe());
        yeuThichData.put("SoLuongGheTrong",chuyenBay.getSoLuongGheTrong());
        yeuThichData.put("SoLuongGheVipTrong",chuyenBay.getSoLuongGheVipTrong());
        yeuThichData.put("TrangThai",chuyenBay.getTrangThai());
        yeuThichData.put("isYeuThich", chuyenBay.isYeuThich());



        yeuThichCollection
                .document(chuyenBay.getIdChuyenBay())
                .set(yeuThichData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Dữ liệu đã được lưu thành công
                        Toast.makeText(ChiTietFlightActivity.this, "Đã thêm vào danh sách yêu thích!", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Đã xảy ra lỗi trong quá trình lưu dữ liệu
                        Toast.makeText(ChiTietFlightActivity.this, "Lỗi khi thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private  void putChuyenBayYeuThich(ChuyenBay chuyenBay){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Map<String, Object> favoriteItem = new HashMap<>();
        favoriteItem.put("itemID", chuyenBay.getIdChuyenBay());
        favoriteItem.put("userID", userID);
        favoriteItem.put("DiemDen", chuyenBay.getDiemDen());
        favoriteItem.put("HinhAnh",chuyenBay.getHinhAnh());
        favoriteItem.put("DiemDi", chuyenBay.getDiemDi());
        favoriteItem.put("GioBatDau",chuyenBay.getGioBatDau());
        favoriteItem.put("NgayDi",chuyenBay.getNgayDi());
        favoriteItem.put("NgayVe",chuyenBay.getNgayVe());
        favoriteItem.put("SoLuongGheTrong",chuyenBay.getSoLuongGheTrong());
        favoriteItem.put("SoLuongGheVipTrong",chuyenBay.getSoLuongGheVipTrong());
        favoriteItem.put("TrangThai",chuyenBay.getTrangThai());
        favoriteItem.put("isYeuThich", chuyenBay.isYeuThich());
        db.collection("favorites")
                .add(favoriteItem)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        tymButton.setBackgroundResource(R.drawable.heart_red_24);
                        Toast.makeText(ChiTietFlightActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isYeuThich_" + chuyenBay.getIdChuyenBay(), chuyenBay.isYeuThich());
                        editor.putInt("checkBoxBackground_" + chuyenBay.getIdChuyenBay(), R.drawable.heart_red_24);
                        editor.apply();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Xử lý khi thêm item thất bại
                        Toast.makeText(ChiTietFlightActivity.this, "Lỗi ", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void xoaDulieuTrenFirestore(ChuyenBay chuyenBay) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference yeuThichCollection = db.collection("YeuThich");
        yeuThichCollection
                .document(chuyenBay.getIdChuyenBay()) // Chúng ta sử dụng ID của chuyến bay làm tên tài liệu
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ChiTietFlightActivity.this, "Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChiTietFlightActivity.this, "Lỗi khi xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}