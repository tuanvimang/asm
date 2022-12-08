package com.example.asm_tkgd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_tkgd.dao.LoginDAO;
import com.example.asm_tkgd.models.TaiKhoan;

public class register extends AppCompatActivity {
    private EditText taiKhoan, matKhau;
    private LoginDAO loginDAO;
    private TextView dangNhap;
    private Button dangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        taiKhoan = findViewById(R.id.taiKhoan);
        matKhau = findViewById(R.id.matKhau);
        loginDAO = new LoginDAO(this);
        dangNhap = findViewById(R.id.dangNhap);
        dangKy = findViewById(R.id.dangKy);
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register ();
            }
        });
        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void register (){
        String user = taiKhoan.getText().toString();
        String pass = matKhau.getText().toString();
        TaiKhoan taiKhoan = new TaiKhoan(user,pass);
        Boolean check = loginDAO.them(taiKhoan);
        if(check==false){
            Toast.makeText(this,"Register failed",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Register success",Toast.LENGTH_LONG).show();
            finish();
        }
    }
}