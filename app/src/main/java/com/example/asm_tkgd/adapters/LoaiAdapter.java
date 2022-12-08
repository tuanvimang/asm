package com.example.asm_tkgd.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_tkgd.R;
import com.example.asm_tkgd.dao.LoaiDAO;
import com.example.asm_tkgd.models.Loai;

import java.util.ArrayList;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Loai> list;
    private LoaiDAO loaiDAO;
    private int trangThai;

    public LoaiAdapter(Context context, ArrayList<Loai> list, LoaiDAO loaiDAO, int trangThai) {
        this.context = context;
        this.list = list;
        this.loaiDAO = loaiDAO;
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate((R.layout.item_loai),parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtID.setText(String.valueOf(list.get(position).getIdLoai()));
        holder.txtTenLoai.setText(list.get(position).getTenLoai() );
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogUpdate(list.get(holder.getAdapterPosition()));
            }
        });

        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = loaiDAO.xoaLoai(list.get(holder.getAdapterPosition()).getIdLoai());
                if(check){
                    getDS();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showDialogUpdate(Loai l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context );
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loai,null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtTenLoai = view.findViewById(R.id.edtTenLoai);
        Button btnThem = view.findViewById(R.id.btnThem);
        Button btnHuy = view.findViewById(R.id.btnHuy);
        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);

        txtTieuDe.setText("CẬP NHẬT LOẠI");
        edtTenLoai.setText(l.getTenLoai());
        btnThem.setText("Cập nhật  ");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edtTenLoai.getText().toString();
                boolean check = loaiDAO.capNhatLoai(new Loai(l.getIdLoai(),data,trangThai));
                if (check==true){
                    getDS();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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

    private void getDS() {
        list.clear();
        list= loaiDAO.layDSLoai(trangThai);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtID, txtTenLoai;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
