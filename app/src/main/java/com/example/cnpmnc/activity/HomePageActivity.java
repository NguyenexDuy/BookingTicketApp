package com.example.cnpmnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.cnpmnc.R;
import com.example.cnpmnc.adapter.Adapterviewpager;
import com.example.cnpmnc.fragment.BookingFragment;
import com.example.cnpmnc.fragment.HomePageFragment;
import com.example.cnpmnc.fragment.NotifiFragment;
import com.example.cnpmnc.fragment.PersonFragment;
import com.example.cnpmnc.model.ChuyenBay;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class HomePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private static final int FRAGMENT_HOMEPAGE = 0;
    private static final int FRAGMENT_BOOKING = 1;
    private static final int FRAGMENT_NOTIFI = 2;
    private static final int FRAGMENT_PERSON = 3;
    private int mCurrentFragment = FRAGMENT_HOMEPAGE;
    private BottomNavigationView mBottomNavigationView;
    private ChuyenBay chuyenBay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Anhxa();
        replaceFragment(new HomePageFragment());
        check();
    }
    private  void check(){
        if (getIntent().getSerializableExtra("Chuyenbay") != null){
            chuyenBay = (ChuyenBay) getIntent().getSerializableExtra("Chuyenbay");
            replaceFragment(new BookingFragment(chuyenBay));
            mCurrentFragment = FRAGMENT_BOOKING;
            mBottomNavigationView.getMenu().findItem(R.id.item_booking).setChecked(true);
        }
    }
    private void Anhxa() {
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item_home){
            if (mCurrentFragment != FRAGMENT_HOMEPAGE){
                replaceFragment(new HomePageFragment());
                mCurrentFragment = FRAGMENT_HOMEPAGE;
            }
        }else if(id == R.id.item_booking){
            if (mCurrentFragment != FRAGMENT_BOOKING){
                replaceFragment(new BookingFragment());
                mCurrentFragment = FRAGMENT_BOOKING;
            }
        }else if(id == R.id.item_noti){
            if (mCurrentFragment != FRAGMENT_NOTIFI){
                replaceFragment(new NotifiFragment());
                mCurrentFragment = FRAGMENT_NOTIFI;
            }
        }else if(id == R.id.item_accout){
            if (mCurrentFragment != FRAGMENT_PERSON){
                replaceFragment(new PersonFragment());
                mCurrentFragment = FRAGMENT_PERSON;
            }
        }
        return true;
    }

}