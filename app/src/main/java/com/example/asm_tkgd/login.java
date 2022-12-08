package com.example.asm_tkgd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_tkgd.activities.MainActivity;
import com.example.asm_tkgd.dao.LoginDAO;
import com.example.asm_tkgd.models.TaiKhoan;

public class login extends AppCompatActivity {
    private EditText taiKhoan, matKhau;
    private LoginDAO loginDAO;
    private TextView dangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readLogin();
        setContentView(R.layout.activity_login);
        taiKhoan = findViewById(R.id.taiKhoan);
        matKhau = findViewById(R.id.matKhau);
        loginDAO = new LoginDAO(login.this);
        dangKi = findViewById(R.id.dangKi);
        dangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
    }

    public void onClickLogin(View view) {
        String user = taiKhoan.getText().toString();
        String pass = matKhau.getText().toString();
        TaiKhoan result = loginDAO.login2(user,pass);

        if(result==null){
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }else {
            saveLogin(result);
            Bundle bundle = new Bundle();
            bundle.putString("user",user);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
    private void saveLogin(TaiKhoan taiKhoan){
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putInt("id",taiKhoan.getId());
        editor.commit();
    }

    private void readLogin(){
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        Boolean isLoggedIn = preferences.getBoolean("isLoggedIn",false);
        if(isLoggedIn){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}