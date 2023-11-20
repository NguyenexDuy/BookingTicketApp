package com.example.cnpmnc.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cnpmnc.R;
import com.example.cnpmnc.activity.ChonChuyenBayActivity;
import com.example.cnpmnc.activity.DangNhapActivity;
import com.example.cnpmnc.activity.TimKiemActivity;
import com.example.cnpmnc.model.ChuyenBay;
import com.example.cnpmnc.model.DiaDiem;
import com.example.cnpmnc.model.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    TextView tv_idsanbaydiemden, tv_tensanbaydiemden, tv_idsanbaydiemdi, tv_tensanbaydiemdi,tv_CalendarNgayVeKhuHoi,tv_CalendarNgayDiKhuHoi,tv_countNguoiLonKhuHoi,tv_count2NguoiLonKhuHoi,tv_count3NguoiLonKhuHoi;
    ImageButton btn_minus1KhuHoi,btn_plus1KhuHoi,btn_minus2KhuHoi,btn_plus2KhuHoi,btn_minus3KhuHoi,btn_plus3KhuHoi;
    Firebase firebase;
    Button btnTimKiemKhuHoi;
    int countNguoiLon=1;
    int countTreEm2_12Tuoi=0;
    int countTreEmDuoi2tuoi=0;
    private String NgayVe;
    private String currentDate;
    private LocalDate curdate;
    FirebaseUser firebaseUser;
    private static final int LOGIN_REQUEST_CODE = 123;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khu_hoi, container, false);
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
        btnTimKiemKhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!=null)
                {
                    ThucHienHanhDong();
                    Toast.makeText(getContext(), "Co user", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), DiaDiem.getInstance().getDiemDen(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(), "ko co user", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Xác nhận");
                    builder.setMessage("Quý khách cần phải đăng nhập để thực hiện đặt chuyến bay?");


                    builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getContext(), DangNhapActivity.class);
                            startActivityForResult(intent, LOGIN_REQUEST_CODE);
                        }
                    });

                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Thực hiện hành động khi người dùng chọn "Không" ở đây
                            dialog.dismiss(); // Dismiss dialog khi chọn "Không"
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                ThucHienHanhDong();
            } else {
                Toast.makeText(getContext(), "Khong thanh cong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void ThucHienHanhDong()
    {
        DiaDiem.getInstance().setSoLuongNguoiLon(tv_countNguoiLonKhuHoi.getText().toString());
        DiaDiem.getInstance().setSoLuongTreEm2Ttoi12T(tv_count2NguoiLonKhuHoi.getText().toString());
        DiaDiem.getInstance().setSoLuongTreEmDuoi2T(tv_count3NguoiLonKhuHoi.getText().toString());
        DiaDiem.getInstance().setDiemDi(tv_tensanbaydiemdi.getText().toString());
        DiaDiem.getInstance().setDiemDen(tv_tensanbaydiemden.getText().toString());
        DiaDiem.getInstance().setNgayDi(tv_CalendarNgayDiKhuHoi.getText().toString());
        DiaDiem.getInstance().setNgayVe(tv_CalendarNgayVeKhuHoi.getText().toString());
        Toast.makeText(getContext(), DiaDiem.getInstance().getNgayVe(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ChonChuyenBayActivity.class);
        getContext().startActivity(intent);
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
    private void Anhxa(View view){
        firebase = new Firebase(getContext());
        btnTimKiemKhuHoi=view.findViewById(R.id.btnTimKiemKhuHoi);
        btn_minus1KhuHoi=view.findViewById(R.id.btn_minusKhuHoi);
        btn_minus2KhuHoi=view.findViewById(R.id.btn_minus2KhuHoi);
        btn_minus3KhuHoi=view.findViewById(R.id.btn_minus3KhuHoi);
        btn_plus1KhuHoi=view.findViewById(R.id.btn_plusKhuHoi);
        btn_plus2KhuHoi=view.findViewById(R.id.btn_plus2KhuHoi);
        btn_plus3KhuHoi=view.findViewById(R.id.btn_plus3KhuHoi);
        tv_countNguoiLonKhuHoi=view.findViewById(R.id.tv_countNguoiLonKhuHoi);
        tv_count2NguoiLonKhuHoi=view.findViewById(R.id.tv_countTreEm2_12KhuHoi);
        tv_count3NguoiLonKhuHoi=view.findViewById(R.id.tv_count3TreEm2TKhuHoi);
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
                public void onCallBack(String idSanBay1) {
                    tv_idsanbaydiemden.setText(idSanBay1);
                }
            });
            tv_tensanbaydiemden.setText(chuyenBay.getDiemDen());
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
    private  void Action()
    {
        btn_minus1KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countNguoiLon>1)
                {
                    countNguoiLon--;
                    updateCount(tv_countNguoiLonKhuHoi,countNguoiLon);
                }

            }
        });
        btn_minus2KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEm2_12Tuoi>0)
                {
                    countTreEm2_12Tuoi--;
                    updateCount(tv_count2NguoiLonKhuHoi,countTreEm2_12Tuoi);
                }
            }
        });
        btn_minus3KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi>0)
                {
                    countTreEmDuoi2tuoi--;
                    updateCount(tv_count3NguoiLonKhuHoi,countTreEmDuoi2tuoi);
                }

            }
        });
        btn_plus1KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countNguoiLon<5)
                {
                    countNguoiLon++;
                    updateCount(tv_countNguoiLonKhuHoi,countNguoiLon);
                }else {
                    Toast.makeText(getContext(), "Số lượng hàng khách đã đạt tối đa", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btn_plus2KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEm2_12Tuoi<4)
                {
                    countTreEm2_12Tuoi++;
                    updateCount(tv_count2NguoiLonKhuHoi,countTreEm2_12Tuoi);
                }else {
                    Toast.makeText(getContext(), "Số lượng hàng khách đã đạt tối đa", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btn_plus3KhuHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countTreEmDuoi2tuoi<4){
                    countTreEmDuoi2tuoi++;
                    updateCount(tv_count3NguoiLonKhuHoi,countTreEmDuoi2tuoi);
                }else {
                    Toast.makeText(getContext(), "Số lượng hàng khách đã đạt tối đa", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateCount(TextView text,int count) {
        text.setText(String.format("%02d", count));
    }
}