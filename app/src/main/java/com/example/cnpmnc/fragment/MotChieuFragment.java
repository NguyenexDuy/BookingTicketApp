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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.TimKiemActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

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
    private String DiemDi, DiemDen;
    public MotChieuFragment() {
        // Required empty public constructor
    }
    public MotChieuFragment(ChuyenBay chuyenBay) {
        this.chuyenBay = chuyenBay;
    }
    public MotChieuFragment(String diemdi, String diemden) {
        this.DiemDi = diemdi;
        this.DiemDen = diemden;
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
    private String currentDate;
    private LocalDate curdate;
    private ImageButton btn_minus1,btn_plus1,btn_minus2,btn_plus2,btn_minus3,btn_plus3;
    private TextView tv_countNguoiLon,tv_count2NguoiLon,tv_count3NguoiLon,tv_idsanbaydiemdi,tv_tensanbaydiemdi,tv_idsanbaydiemden,tv_tensanbaydiemden;
    Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mot_chieu, container, false);
        Anhxa(view);
        return view;
    }
    private void Anhxa(View view) {
        tv_CalendarNgayDi=view.findViewById(R.id.tv_CalendarNgayDi);
        tv_countNguoiLon=view.findViewById(R.id.tv_countNguoiLonMotChieu);
        btn_minus1=view.findViewById(R.id.btn_minusMotChieu);
        btn_plus1=view.findViewById(R.id.btn_plusMotChieu);
        btn_minus2=view.findViewById(R.id.btn_minus2MotChieu);
        btn_plus2=view.findViewById(R.id.btn_plus2MotChieu);
        btn_minus3=view.findViewById(R.id.btn_minus3MotChieu);
        btn_plus3=view.findViewById(R.id.btn_plus3MotChieu);
        tv_count2NguoiLon=view.findViewById(R.id.tv_count2NguoiLonMotChieu);
        tv_count3NguoiLon=view.findViewById(R.id.tv_count3NguoiLonMotChieu);
        tv_idsanbaydiemdi=view.findViewById(R.id.tv_idsanbaydiemdiMotChieu);
        tv_idsanbaydiemden=view.findViewById(R.id.tv_idsanbaydiemdenMotChieu);
        tv_tensanbaydiemdi=view.findViewById(R.id.tv_tensanbaydiemdiMotChieu);
        tv_tensanbaydiemden=view.findViewById(R.id.tv_tensanbaydiemdenMotChieu);
        firebase = new Firebase(getContext());
        currentDate = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        curdate = LocalDate.parse(currentDate, formatter);
        tv_CalendarNgayDi.setText(currentDate);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setdata();

        tv_idsanbaydiemdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TimKiemActivity.class);
                intent.putExtra("Chuyenbay", chuyenBay);
                intent.putExtra("Timkiem", "diemdi");
                startActivity(intent);
            }
        });
        tv_CalendarNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarNgayDi();
            }
        });
        btn_minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countNguoiLon>0)
                {
                    countNguoiLon--;
                    updateCount(tv_countNguoiLon,countNguoiLon);
                }

            }
        });
        btn_minus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEm2_12Tuoi>0)
                {
                    countTreEm2_12Tuoi--;
                    updateCount(tv_count2NguoiLon,countTreEm2_12Tuoi);
                }

            }
        });
        btn_minus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi>0)
                {
                    countTreEmDuoi2tuoi--;
                    updateCount(tv_count3NguoiLon,countTreEmDuoi2tuoi);
                }

            }
        });
        btn_plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countNguoiLon++;
                updateCount(tv_countNguoiLon,countNguoiLon);
            }
        });
        btn_plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEm2_12Tuoi++;
                updateCount(tv_count2NguoiLon,countTreEm2_12Tuoi);
            }
        });
        btn_plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEmDuoi2tuoi++;
                updateCount(tv_count3NguoiLon,countTreEmDuoi2tuoi);
            }
        });
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
        }else if (DiemDi != null){
            tv_idsanbaydiemdi.setText(DiemDi);
            firebase.getTenSanBayBySanBayId(DiemDi, new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemdi.setText(tensanbay);
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
}