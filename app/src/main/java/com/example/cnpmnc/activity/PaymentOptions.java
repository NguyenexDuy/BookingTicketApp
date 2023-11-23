package com.example.cnpmnc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.HangKhach;
import com.example.cnpmnc.model.HangKhachDataHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentOptions extends AppCompatActivity {
    String PublishableKey = "pk_test_51OCkphH0M8gfVgbL3402cevMkpx7e9ZgY3dowKdHtYyitkRqFsig1O3w0wP9vWeCn6XEMHBIHfeQ0XNzrHv4g5Hy00goIhYCrg";
    String SecretKey = "sk_test_51OCkphH0M8gfVgbLnhaXkmrmQHqXNF7NAxHNsazNoO4ANaoLAepk5VbvJEswwrC5Wc3jjbjkF0ug9z53uVTgxCEB00uEEoXZ4C";
    String CustomerId;
    String EphericalKey;
    String ClientSecret;
    PaymentSheet paymentSheet;
    LinearLayout linear_thanhtoan;
    ChuyenBay chuyenBay;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private int numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi,soLuongHangKhach, price,GiaVeTong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);

        btn_ThanhToanStripe = findViewById(R.id.btn_ThanhToanStripe);


        chuyenBay=(ChuyenBay) getIntent().getSerializableExtra("DuLieuChuyenBay");
        linear_thanhtoan=findViewById(R.id.linear_thanhtoan);

//        price=Integer.valueOf(chuyenBay.getGiaVe());


        tv_countNguoiLonKhuHoi=findViewById(R.id.tv_countNguoiLonKhuHoi);
        tv_count2NguoiLonKhuHoi=findViewById(R.id.tv_countTreEm2_12KhuHoi);
        tv_count3NguoiLonKhuHoi=findViewById(R.id.tv_count3TreEm2TKhuHoi);
        tv_CalendarNgayVeKhuHoi=findViewById(R.id.tv_CalendarNgayVeKhuHoi);
        tv_CalendarNgayDiKhuHoi=findViewById(R.id.tv_CalendarNgayDiKhuHoi);
        tv_idsanbaydiemdi = findViewById(R.id.tv_idsanbaydiemdi);
        tv_tensanbaydiemdi =findViewById(R.id.tv_tensanbaydiemdi);
        tv_idsanbaydiemden = findViewById(R.id.tv_idsanbaydiemden);
        tv_tensanbaydiemden = findViewById(R.id.tv_tensanbaydiemden);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            chuyenBay = (ChuyenBay) extras.getSerializable("DuLieuChuyenBay");
            if (chuyenBay != null) {
                price = Integer.valueOf(chuyenBay.getGiaVe());
                numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
                numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
                numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
                soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
                GiaVeTong=price*soLuongHangKhach;
            } else {
                Toast.makeText(this, "Error: ChuyenBay is null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: Intent extras are null", Toast.LENGTH_SHORT).show();
        }
        PaymentConfiguration.init(this, PublishableKey);

        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            if (paymentSheetResult != null) {
                onPaymentResult(paymentSheetResult);
            } else {
                Log.d("PaymentOptions", "paymentSheetResult is null");
            }
        });



        linear_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStripeCustomer();

                if (CustomerId != null) {
                    paymentFlow();
                } else {
                }
            }
        });

    }
  private void AddVeMayBay1() {
        if (chuyenBay != null) {
            HangKhachDataHolder dataHolder = HangKhachDataHolder.getInstance();
            ArrayList<HangKhach> hangKhachList = dataHolder.getHangKhachList();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String idChuyenBay = chuyenBay.getIdChuyenBay();
            String diemDi = chuyenBay.getDiemDi();
            String diemDen = chuyenBay.getDiemDen();
            String giaVe = String.valueOf(GiaVeTong);
            String ngayBay = chuyenBay.getNgayDi();
            String ngayVe = chuyenBay.getNgayVe();
            String gioDi = chuyenBay.getGioBatDau();
            String gioVe = chuyenBay.getGioVe();

            Map<String, Object> hangKhachData = new HashMap<>();
            for (int i = 0; i < hangKhachList.size(); i++) {
                HangKhach hangKhach = hangKhachList.get(i);
                Map<String, Object> hangKhachMap = new HashMap<>();
                hangKhachMap.put("name", hangKhach.getHoTen());
                hangKhachMap.put("type", hangKhach.getType());
                hangKhachMap.put("soGhe", hangKhach.getSoghe());
                hangKhachData.put("hangKhach_" + i, hangKhachMap);
            }
            hangKhachData.put("ChuyenBayID", idChuyenBay);
            hangKhachData.put("KhachHangID", userId);
            hangKhachData.put("diemDi", diemDi);
            hangKhachData.put("diemDen", diemDen);
            hangKhachData.put("gioDi", gioDi);
            hangKhachData.put("gioVe", gioVe);
            hangKhachData.put("giaVe", giaVe);
            hangKhachData.put("ngayBatDau", ngayBay);
            hangKhachData.put("ngayVe", ngayVe);

            db.collection("VeMayBay").add(hangKhachData)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Tải thành công", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Lỗi: ChuyenBay là null", Toast.LENGTH_SHORT).show();
        }
    }
 
        HangKhachDataHolder dataHolder = HangKhachDataHolder.getInstance();
        ArrayList<HangKhach> hangKhachList = dataHolder.getHangKhachList();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String idChuyenBay=chuyenBay.getIdChuyenBay();
        String diemDi=chuyenBay.getDiemDi();
        String diemDen=chuyenBay.getDiemDen();
        String giaVe= String.valueOf(GiaVeTong);
        String ngayBay=chuyenBay.getNgayDi();
        String ngayVe=chuyenBay.getNgayVe();
        String gioDi=chuyenBay.getGioBatDau();
        String gioVe=chuyenBay.getGioVe();

        Map<String, Object> hangKhachData = new HashMap<>();
        for (int i = 0; i < hangKhachList.size(); i++) {
            HangKhach hangKhach = hangKhachList.get(i);
            Map<String, Object> hangKhachMap = new HashMap<>();
            hangKhachMap.put("name", hangKhach.getHoTen());
            hangKhachMap.put("type", hangKhach.getType());
            hangKhachMap.put("soGhe",hangKhach.getSoghe());
            hangKhachData.put("hangKhach_" + i, hangKhachMap);
        }
        hangKhachData.put("ChuyenBayID",idChuyenBay);
        hangKhachData.put("KhachHangID",userId);
        hangKhachData.put("diemDi",diemDi);
        hangKhachData.put("diemDen",diemDen);
        hangKhachData.put("gioDi",gioDi);
        hangKhachData.put("gioVe",gioVe);
        hangKhachData.put("giaVe",giaVe+ "VND");
        hangKhachData.put("ngayBatDau",ngayBay);
        hangKhachData.put("ngayVe",ngayVe);


        db.collection("VeMayBay").add(hangKhachData).addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                });


    }



    private void updateBooking() {
        String idChuyenBay = chuyenBay.getIdChuyenBay();
        HangKhachDataHolder dataHolder = HangKhachDataHolder.getInstance();
        ArrayList<HangKhach> hangKhachList = dataHolder.getHangKhachList();

        for (int i = 0; i < hangKhachList.size(); i++) {
            HangKhach hangKhach = hangKhachList.get(i);
            Long soghe = hangKhach.getSoghe();

            // Reference to the "ghe" collection and query for matching documents
            db.collection("ghe")
                    .whereEqualTo("IdChuyenBay", idChuyenBay)
                    .whereEqualTo("soGhe", soghe)
                    .get()
                    .addOnSuccessListener(queryDocumentSnapshots -> {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            documentSnapshot.getReference().update("isBooked", true)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("Capnhatghe", "Cập nhật thành công cho ghế: " + soghe);
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.w("Capnhatghe", "Lỗi khi cập nhật cho ghế: " + soghe, e);
                                    });
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.w("Capnhatghe", "Lỗi khi truy vấn danh sách ghế", e);
                    });
        }
    }

    private void updateSoLuongGheTrong() {
        String idChuyenBay = chuyenBay.getIdChuyenBay();

        // Tìm và cập nhật số lượng ghế trống sau khi thanh toán
        db.collection("ChuyenBay")
                .document(idChuyenBay)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String soLuongGheTrong = document.getString("SoLuongGheTrong");
                            ArrayList<HangKhach> hangKhachList = HangKhachDataHolder.getInstance().getHangKhachList();

                            int soLuongGheTrongInt = Integer.parseInt(soLuongGheTrong);
                            soLuongGheTrongInt -= hangKhachList.size();
                            String updatedSoLuongGheTrong = String.valueOf(soLuongGheTrongInt);

                            db.collection("ChuyenBay")
                                    .document(idChuyenBay)
                                    .update("SoLuongGheTrong", updatedSoLuongGheTrong)
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("PaymentOptions", "Số lượng ghế trống đã được cập nhật.");
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.w("PaymentOptions", "Lỗi khi cập nhật số lượng ghế trống.", e);
                                    });
                        } else {
                            Log.d("PaymentOptions", "Không tìm thấy tài liệu.");
                        }
                    } else {
                        Log.d("PaymentOptions", "Lỗi khi truy cập dữ liệu: ", task.getException());
                    }
                });
    }
    private void createStripeCustomer() {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        CustomerId = object.optString("id");
                        Toast.makeText(PaymentOptions.this, CustomerId, Toast.LENGTH_SHORT).show();
                        getEphericalKey();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(PaymentOptions.this, "Error creating customer: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SecretKey);
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(PaymentOptions.this);
        requestQueue.add(request);
    }

    private void getEphericalKey() {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        EphericalKey = object.optString("id");
                        Toast.makeText(PaymentOptions.this, EphericalKey, Toast.LENGTH_SHORT).show();
                        getClientSecret(CustomerId, EphericalKey);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(PaymentOptions.this, "Error getting EphericalKey: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SecretKey);
                header.put("Stripe-Version", "2023-10-16");
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("customer", CustomerId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void getClientSecret(String customerId, String ephericalKey) {
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        ClientSecret = object.optString("client_secret");

                        if (ClientSecret != null && !ClientSecret.isEmpty()) {
                            paymentFlow(); // Call paymentFlow once ClientSecret is obtained
                        } else {
                            Toast.makeText(PaymentOptions.this, "Error: Empty or null ClientSecret", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(PaymentOptions.this, "Error getting ClientSecret: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + SecretKey);
                return header;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerId);
                params.put("amount", String.valueOf(GiaVeTong));
                params.put("currency", "USD"); // Replace with your currency
                // Add any other necessary parameters
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    private void paymentFlow() {
        if (ClientSecret == null) {
            Toast.makeText(PaymentOptions.this, "Error: ClientSecret is null", Toast.LENGTH_SHORT).show();
        } else {
            paymentSheet.presentWithPaymentIntent(ClientSecret, new PaymentSheet.Configuration("Learn with Arvind",
                    new PaymentSheet.CustomerConfiguration(CustomerId, EphericalKey)));
        }
    }


    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {
        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {

            updateSoLuongGheTrong();
            AddVeMayBay();
            updateBooking();
            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        }
    }
}

