package com.example.cnpmnc.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ThongTinChoNgoiActivity;
import com.example.cnpmnc.activity.ThongTinKhachhangActivity;
import com.example.cnpmnc.activity.TimKiemActivity;
import com.example.cnpmnc.adapter.TimKiemDiemDiAdapter;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.Firebase;
import com.example.cnpmnc.model.HoaDon;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MotChieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MotChieuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ChuyenBay chuyenBay;
    public MotChieuFragment() {
        // Required empty public constructor
    }
    public MotChieuFragment(ChuyenBay chuyenBay) {
        this.chuyenBay = chuyenBay;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MotChieuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MotChieuFragment newInstance(String param1, String param2) {
        MotChieuFragment fragment = new MotChieuFragment();
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
    private TextView tv_CalendarNgayDi;
    int countNguoiLon=0;
    int countTreEm2_12Tuoi=0;
    int countTreEmDuoi2tuoi=0;
    private String NgayDi;
    private Button btnTimKiemMotChieu;
    private String currentDate;
    private LocalDate curdate;
    private ImageButton btn_plus1MotChieu,btn_plus2MotChieu,btn_plus3MotChieu,btn_minus1MotChieu,btn_minus2MotChieu,btn_minus3MotChieu;
    private TextView tv_countNguoiLon,tv_countTreEm2_12T,tv_countTreEm2T,tv_idsanbaydiemdi,tv_tensanbaydiemdi,tv_idsanbaydiemden,tv_tensanbaydiemden;
    Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mot_chieu, container, false);
        Anhxa(view);
        Action();
        setdata();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_idsanbaydiemdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TimKiemActivity.class);
                intent.putExtra("Timkiem", "diemdi");
                startActivity(intent);
            }
        });
        tv_idsanbaydiemden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TimKiemActivity.class);
                intent.putExtra("Timkiem","diemden");
                startActivity(intent);
            }
        });
        tv_CalendarNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarNgayDi();
            }
        });

        btnTimKiemMotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), ThongTinKhachhangActivity.class);

                if(chuyenBay!=null)
                {
                    HoaDon hoaDon=new HoaDon(chuyenBay.getDiemDi(),chuyenBay.getDiemDen(),tv_countNguoiLon.getText().toString(),tv_countTreEm2_12T.getText().toString(),tv_countTreEm2T.getText().toString());
                    intent.putExtra("ThongTinChuyenBay",chuyenBay);
                    Toast.makeText(getContext(), "chuyenbay", Toast.LENGTH_SHORT).show();
                } else if (DiaDiem.getInstance().getDiemDi() != null&&DiaDiem.getInstance().getDiemDen() != null) {
                    Toast.makeText(getContext(), "abc", Toast.LENGTH_SHORT).show();
                    Bundle bundle=new Bundle();
                    bundle.putString("DiemDen",DiaDiem.getInstance().getDiemDen());
                    bundle.putString("DiemDi",DiaDiem.getInstance().getDiemDi());
//                    Intent intent1=new Intent(getContext(),ThongTinKhachhangActivity.class);
//                    intent1.putExtra(bundle);
                } else {
                    Toast.makeText(getContext(), "NGU", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setdata(){
        if (chuyenBay != null){

            firebase.getIdSanBayByTenSanBay(chuyenBay.getDiemDi(), new Firebase.getIdSanBayByTenSanBayCallback() {
                @Override
                public void onCallBack(String idSanBay) {
                    tv_idsanbaydiemdi.setText(idSanBay);
                }
            });
            tv_tensanbaydiemdi.setText(chuyenBay.getDiemDi());

            firebase.getIdSanBayByTenSanBay(chuyenBay.getDiemDen(), new Firebase.getIdSanBayByTenSanBayCallback() {
                @Override
                public void onCallBack(String idSanBay) {
                    tv_idsanbaydiemden.setText(idSanBay);
                }
            });
            tv_tensanbaydiemden.setText(chuyenBay.getDiemDen());

//
//            tv_idsanbaydiemdi.setText(chuyenBay.getDiemDi());
//            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDi(), new Firebase.getTenSanBayBySanBayIdCallback() {
//                @Override
//                public void onCallback(String tensanbay) {
//                    tv_tensanbaydiemdi.setText(tensanbay);
//                }
//            });
//
//
//            tv_idsanbaydiemden.setText(chuyenBay.getDiemDen());
//            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDen(), new Firebase.getTenSanBayBySanBayIdCallback() {
//                @Override
//                public void onCallback(String tensanbay) {
//                    tv_tensanbaydiemden.setText(tensanbay);
//                }
//            });
        }
        if (DiaDiem.getInstance().getDiemDi() != null){
            String diemdi = DiaDiem.getInstance().getDiemDi();
            tv_idsanbaydiemdi.setText(diemdi);
            firebase.getTenSanBayBySanBayId(diemdi, new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemdi.setText(tensanbay);
                }
            });
        }
        if (DiaDiem.getInstance().getDiemDen() != null){
            String diemden = DiaDiem.getInstance().getDiemDen();
            tv_idsanbaydiemden.setText(diemden);
            firebase.getTenSanBayBySanBayId(diemden, new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemden.setText(tensanbay);
                }
            });
        }
    }
    private void showCalendarNgayDi(){
        final Calendar c = Calendar.getInstance();
        long currentDateInMillis = c.getTimeInMillis();

        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog rentDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        NgayDi = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                        LocalDate ngaydi = LocalDate.parse(NgayDi, formatter);
                        if (curdate.isAfter(ngaydi)){
                            Toast.makeText(getContext(), "Ngày thuê không hợp lệ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        tv_CalendarNgayDi.setText(NgayDi);
                    }
                }, mYear, mMonth, mDay);
        rentDatePickerDialog.setTitle("Chọn ngày thuê");
        rentDatePickerDialog.getDatePicker().setMinDate(currentDateInMillis);
        rentDatePickerDialog.show();
    }
    private void updateCount(TextView text,int count) {
        text.setText(String.format("%02d", count));
    }

    private  void Action()
    {
        btn_minus1MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countNguoiLon>0)
                {
                    countNguoiLon--;
                    updateCount(tv_countNguoiLon,countNguoiLon);
                }

            }
        });
        btn_minus2MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEm2_12Tuoi>0)
                {
                    countTreEm2_12Tuoi--;
                    updateCount(tv_countTreEm2_12T,countTreEm2_12Tuoi);
                }

            }
        });
        btn_minus3MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi>0)
                {
                    countTreEmDuoi2tuoi--;
                    updateCount(tv_countTreEm2T,countTreEmDuoi2tuoi);
                }

            }
        });
        btn_plus1MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countNguoiLon++;
                updateCount(tv_countNguoiLon,countNguoiLon);
            }
        });
        btn_plus2MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEm2_12Tuoi++;
                updateCount(tv_countTreEm2_12T,countTreEm2_12Tuoi);
            }
        });
        btn_plus3MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEmDuoi2tuoi++;
                updateCount(tv_countTreEm2T,countTreEmDuoi2tuoi);
            }
        });
    }
    private void Anhxa(View view) {
        btn_plus1MotChieu=view.findViewById(R.id.btn_plusMotChieu);
        btn_plus2MotChieu=view.findViewById(R.id.btn_plus2MotChieu);
        btn_plus3MotChieu=view.findViewById(R.id.btn_plus3MotChieu);
        btn_minus1MotChieu=view.findViewById(R.id.btn_minusMotChieu);
        btn_minus2MotChieu=view.findViewById(R.id.btn_minus2MotChieu);
        btn_minus3MotChieu=view.findViewById(R.id.btn_minus3MotChieu);
        tv_countNguoiLon=view.findViewById(R.id.tv_countNguoiLonMotChieu);
        tv_countTreEm2T=view.findViewById(R.id.tv_countTrEm2TMotChieu);
        tv_countTreEm2_12T=view.findViewById(R.id.tv_countTreEm2_12TMotChieu);
        tv_CalendarNgayDi=view.findViewById(R.id.tv_CalendarNgayDi);
        tv_idsanbaydiemdi=view.findViewById(R.id.tv_idsanbaydiemdi);
        tv_idsanbaydiemden=view.findViewById(R.id.tv_idsanbaydiemden);
        tv_tensanbaydiemdi=view.findViewById(R.id.tv_tensanbaydiemdi);
        btnTimKiemMotChieu=view.findViewById(R.id.btnTimKiemMotChieu);
        tv_tensanbaydiemden=view.findViewById(R.id.tv_tensanbaydiemden);
        firebase = new Firebase(getContext());
        currentDate = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        curdate = LocalDate.parse(currentDate, formatter);
        tv_CalendarNgayDi.setText(currentDate);
    }
}