package com.example.asm_tkgd.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_tkgd.R;
import com.example.asm_tkgd.adapters.LoaiAdapter;
import com.example.asm_tkgd.dao.LoaiDAO;
import com.example.asm_tkgd.models.Loai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiFragment extends Fragment {
    private RecyclerView recyclerLoai;
    private FloatingActionButton floatAdd;
    private LoaiDAO loaiDAO;
    private int trangThai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai,container,false);
        recyclerLoai = view.findViewById(R.id.recylerLoai);
        floatAdd = view.findViewById(R.id.floatAdd);
        Bundle bundle = getArguments();
        trangThai = bundle.getInt("trangThai");
        loaiDAO = new LoaiDAO(getContext());
        getDS();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialoAdd();
            }
        });

        return view;
    }

    private void showDialoAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loai,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("THÊM LOẠI ");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtTenLoai.getText().toString();
                boolean check = loaiDAO.themLoai(new Loai(data,trangThai));
                if (check==true){
                    getDS();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    private void getDS(){
        ArrayList<Loai> list = loaiDAO.layDSLoai(trangThai);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerLoai.setLayoutManager(gridLayoutManager);
        LoaiAdapter adapter = new LoaiAdapter(getContext(),list,loaiDAO,trangThai);
        recyclerLoai.setAdapter(adapter);
    }
}
