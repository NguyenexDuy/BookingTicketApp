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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.TimKiemActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.Firebase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private ImageView img_calendar1ChieuNgayDi;
    private TextView tv_CalendarNgayDi;
    int day;
    int month;
    int year;
    int countNguoiLon=0;
    int countTreEm2_12Tuoi=0;
    int countTreEmDuoi2tuoi=0;

    private ImageButton btn_minus1MotChieu,btn_plus1MotChieu,btn_minus2MotChieu,btn_plus2MotChieu,btn_minus3MotChieu,btn_plus3MotChieu;
    private TextView tv_countNguoiLon,tv_count2NguoiLonMotChieu,tv_count3NguoiLonMotChieu,tv_idsanbaydiemdiMotChieu,tv_tensanbaydiemdiMotChieu,tv_idsanbaydiemdenMotChieu,tv_tensanbaydiemdenMotChieu;
    private LinearLayout linear_DiemDi;
    Firebase firebase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mot_chieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_calendar1ChieuNgayDi=view.findViewById(R.id.img_calendar1ChieuNgayDi);
        tv_CalendarNgayDi=view.findViewById(R.id.tv_CalendarNgayDi);
        tv_countNguoiLon=view.findViewById(R.id.tv_countNguoiLonMotChieu);
        btn_minus1MotChieu=view.findViewById(R.id.btn_minusMotChieu);
        btn_plus1MotChieu=view.findViewById(R.id.btn_plusMotChieu);
        btn_minus2MotChieu=view.findViewById(R.id.btn_minus2MotChieu);
        btn_plus2MotChieu=view.findViewById(R.id.btn_plus2MotChieu);
        btn_minus3MotChieu=view.findViewById(R.id.btn_minus3MotChieu);
        btn_plus3MotChieu=view.findViewById(R.id.btn_plus3MotChieu);
        tv_count2NguoiLonMotChieu=view.findViewById(R.id.tv_count2NguoiLonMotChieu);
        tv_count3NguoiLonMotChieu=view.findViewById(R.id.tv_count3NguoiLonMotChieu);
        linear_DiemDi=view.findViewById(R.id.linear_DiemDi);
        tv_idsanbaydiemdiMotChieu=view.findViewById(R.id.tv_idsanbaydiemdiMotChieu);
        tv_idsanbaydiemdenMotChieu=view.findViewById(R.id.tv_idsanbaydiemdenMotChieu);
        tv_tensanbaydiemdiMotChieu=view.findViewById(R.id.tv_tensanbaydiemdiMotChieu);
        tv_tensanbaydiemdenMotChieu=view.findViewById(R.id.tv_tensanbaydiemdenMotChieu);
        firebase = new Firebase(getContext());
        setdata();

        linear_DiemDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), TimKiemActivity.class);
                startActivity(intent);
            }
        });

        Calendar calendar=Calendar.getInstance();
        img_calendar1ChieuNgayDi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=calendar.get(Calendar.YEAR);
                day=calendar.get(Calendar.DAY_OF_MONTH);
                month=calendar.get(Calendar.MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tv_CalendarNgayDi.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        img_calendar1ChieuNgayDi.setVisibility(View.INVISIBLE);
                        tv_CalendarNgayDi.setVisibility(View.VISIBLE);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

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
                    updateCount(tv_count2NguoiLonMotChieu,countTreEm2_12Tuoi);
                }

            }
        });
        btn_minus3MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi>0)
                {
                    countTreEmDuoi2tuoi--;
                    updateCount(tv_count3NguoiLonMotChieu,countTreEmDuoi2tuoi);
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
                updateCount(tv_count2NguoiLonMotChieu,countTreEm2_12Tuoi);
            }
        });
        btn_plus3MotChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countTreEmDuoi2tuoi++;
                updateCount(tv_count3NguoiLonMotChieu,countTreEmDuoi2tuoi);
            }
        });


    }
    private void setdata(){
        if (chuyenBay != null){
            tv_idsanbaydiemdiMotChieu.setText(chuyenBay.getDiemDi());
            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDi(), new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemdiMotChieu.setText(tensanbay);
                }
            });
            tv_idsanbaydiemdenMotChieu.setText(chuyenBay.getDiemDen());
            firebase.getTenSanBayBySanBayId(chuyenBay.getDiemDen(), new Firebase.getTenSanBayBySanBayIdCallback() {
                @Override
                public void onCallback(String tensanbay) {
                    tv_tensanbaydiemdenMotChieu.setText(tensanbay);
                }
            });
        }
    }

    private void updateCount(TextView text,int count) {
        text.setText(String.format("%02d", count));
    }
}