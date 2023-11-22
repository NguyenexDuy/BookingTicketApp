package com.example.cnpmnc.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cnpmnc.R;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.HangKhach;
import com.example.cnpmnc.model.HangKhachDataHolder;
import com.google.firebase.auth.FirebaseAuth;
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
    Button btn_ThanhToanStripe;
    String PublishableKey = "pk_test_51OCkphH0M8gfVgbL3402cevMkpx7e9ZgY3dowKdHtYyitkRqFsig1O3w0wP9vWeCn6XEMHBIHfeQ0XNzrHv4g5Hy00goIhYCrg";
    String SecretKey = "sk_test_51OCkphH0M8gfVgbLnhaXkmrmQHqXNF7NAxHNsazNoO4ANaoLAepk5VbvJEswwrC5Wc3jjbjkF0ug9z53uVTgxCEB00uEEoXZ4C";
    String CustomerId;
    String EphericalKey;
    String ClientSecret;
    PaymentSheet paymentSheet;
    LinearLayout linear_thanhtoan;
    ChuyenBay chuyenBay;
    private int numberTreEm2_12Tuoi, numberNguoiLon, numberTreEm2Tuoi,soLuongHangKhach, price,GiaVeTong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_options);
        btn_ThanhToanStripe = findViewById(R.id.btn_ThanhToanStripe);
        chuyenBay=(ChuyenBay) getIntent().getSerializableExtra("DuLieuChuyenBay");
        linear_thanhtoan=findViewById(R.id.linear_thanhtoan);
        numberTreEm2_12Tuoi =Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEm2Ttoi12T());
        numberNguoiLon=Integer.parseInt(DiaDiem.getInstance().getSoLuongNguoiLon());
        numberTreEm2Tuoi=Integer.parseInt(DiaDiem.getInstance().getSoLuongTreEmDuoi2T());
        soLuongHangKhach=numberNguoiLon+numberTreEm2Tuoi+numberTreEm2_12Tuoi;
        price=Integer.valueOf(chuyenBay.getGiaVe());
        GiaVeTong=price*soLuongHangKhach;
        PaymentConfiguration.init(this, PublishableKey);

        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {
            if (paymentSheetResult != null) {
                onPaymentResult(paymentSheetResult);
            } else {
                Log.d("PaymentOptions", "paymentSheetResult is null");
                // Handle the case where paymentSheetResult is null
                // This might need further investigation or handling
            }
        });



        linear_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStripeCustomer();

                if (CustomerId != null) {
                    paymentFlow();
                } else {
                    Toast.makeText(PaymentOptions.this, "Error: Customer ID is null", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void AddVeMayBay()
    {

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
        hangKhachData.put("giaVe",giaVe);
        hangKhachData.put("ngayBatDau",ngayBay);
        hangKhachData.put("ngayVe",ngayVe);


        db.collection("VeMayBay").add(hangKhachData).addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Tải thành công", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
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
                params.put("amount", "10000");
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
            AddVeMayBay();
            Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show();
        }
    }
}

