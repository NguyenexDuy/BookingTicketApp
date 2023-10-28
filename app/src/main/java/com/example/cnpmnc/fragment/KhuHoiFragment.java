package com.example.cnpmnc.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
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
 * Use the {@link KhuHoiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhuHoiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ChuyenBay chuyenBay;
    private String DiemDi, DiemDen;
    public KhuHoiFragment() {
        // Required empty public constructor
    }
    public KhuHoiFragment(ChuyenBay chuyenbay) {
        this.chuyenBay = chuyenbay;
    }
    public KhuHoiFragment(String diemdi, String diemden) {
        this.DiemDi = diemdi;
        this.DiemDen = diemden;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KhuHoiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhuHoiFragment newInstance(String param1, String param2) {
        KhuHoiFragment fragment = new KhuHoiFragment();
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
    TextView tv_idsanbaydiemden, tv_tensanbaydiemden, tv_idsanbaydiemdi, tv_tensanbaydiemdi,tv_CalendarNgayVeKhuHoi,tv_CalendarNgayDiKhuHoi;
    Firebase firebase;
    private String NgayVe;
    private String currentDate;
    private LocalDate curdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khu_hoi, container, false);
        Anhxa(view);
        setdata();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_CalendarNgayVeKhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarNgayVe(tv_CalendarNgayVeKhuHoi);
            }
        });
        tv_CalendarNgayDiKhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarNgayVe(tv_CalendarNgayDiKhuHoi);
            }
        });
    }

    private void showCalendarNgayVe(TextView textView) {
        final Calendar c = Calendar.getInstance();
        long currentDateInMillis = c.getTimeInMillis();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog rentDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                NgayVe = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate ngaydi = LocalDate.parse(NgayVe, formatter);
                if (curdate.isAfter(ngaydi)){
                    Toast.makeText(getContext(), "Ngày thuê không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setText(NgayVe);
            }
        }, mYear, mMonth, mDay);
        rentDatePickerDialog.setTitle("Chọn ngày thuê");
        rentDatePickerDialog.getDatePicker().setMinDate(currentDateInMillis);
        rentDatePickerDialog.show();
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
    private void Anhxa(View view){
        firebase = new Firebase(getContext());
        tv_CalendarNgayVeKhuHoi=view.findViewById(R.id.tv_CalendarNgayVeKhuHoi);
        tv_CalendarNgayDiKhuHoi=view.findViewById(R.id.tv_CalendarNgayDiKhuHoi);
        tv_idsanbaydiemdi = view.findViewById(R.id.tv_idsanbaydiemdi);
        tv_tensanbaydiemdi = view.findViewById(R.id.tv_tensanbaydiemdi);
        tv_idsanbaydiemden = view.findViewById(R.id.tv_idsanbaydiemden);
        tv_tensanbaydiemden = view.findViewById(R.id.tv_tensanbaydiemden);
        currentDate = new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        curdate = LocalDate.parse(currentDate, formatter);
        tv_CalendarNgayVeKhuHoi.setText(currentDate);
        tv_CalendarNgayDiKhuHoi.setText(currentDate);
    }
}