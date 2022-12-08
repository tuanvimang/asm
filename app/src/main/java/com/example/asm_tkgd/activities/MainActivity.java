package com.example.asm_tkgd.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asm_tkgd.R;
import com.example.asm_tkgd.dao.LoginDAO;
import com.example.asm_tkgd.fragment.GioiThieuFragment;
import com.example.asm_tkgd.fragment.ThongKeFragment;
import com.example.asm_tkgd.fragment.ThuChiFragment;
import com.example.asm_tkgd.login;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
  private   Toolbar toolbar;
  private   DrawerLayout drawerLayout;
  private   LinearLayout linearLayout;
  private NavigationView navigationView;
  private LoginDAO loginDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        linearLayout = findViewById(R.id.linearLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        Fragment _fragment = null;
        Bundle _bundle = new Bundle();
        _fragment = new ThuChiFragment();
        _bundle.putInt("trangThai",1);
        _fragment.setArguments(_bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.linearLayout,_fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
//        dùng để tương tác với headerview
//        View view = navigationView.getHeaderView(0);
//        TextView textView = view.findViewById(R.id.txtTen);
//        Bundle bundle = getArguments();
//        String ten = "";

//        *******************
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();

                switch (item.getItemId()){
                    case R.id.menuThu:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangThai",0);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuChi:
                        fragment = new ThuChiFragment();
                        bundle.putInt("trangThai",1);
                        fragment.setArguments(bundle);
                        break;
                    case R.id.menuThongKe:
                        fragment = new ThongKeFragment();
                        break;
                    case R.id.menuGioiThieu:
                        fragment = new GioiThieuFragment();
                        break;
                    case R.id.menuThoat:
                        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.remove("isLoggedIn");
                        editor.remove("id");
                        editor.commit();
                        editor.apply();
                        Intent intent= new Intent(MainActivity.this, login.class);
                        startActivity(intent);
                        finish();
                        break;
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.linearLayout,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}