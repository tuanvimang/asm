package com.example.asm_tkgd.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_tkgd.R;
import com.example.asm_tkgd.dao.KhoanDAO;
import com.example.asm_tkgd.dao.LoaiDAO;
import com.example.asm_tkgd.models.Khoan;
import com.example.asm_tkgd.models.Loai;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class KhoanAdapter extends RecyclerView.Adapter<KhoanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Khoan> list;
    private KhoanDAO khoanDAO;
    private int trangThai;
    private String idLoai;


    public KhoanAdapter(Context context, ArrayList<Khoan> list, KhoanDAO khoanDAO,int trangThai) {
        this.context = context;
        this.list = list;
        this.khoanDAO = khoanDAO;
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khoan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtIDKhoan.setText(String.valueOf(list.get(position).getIdKhoan()));
            holder.txtKhoan.setText(list.get(position).getKhoan());
            holder.txtTien.setText(String.valueOf(list.get(position).getTien()));
            holder.txtNgay.setText(list.get(position).getNgay());
            holder.txtTenLoai.setText("Loại: "+list.get(position).getTenLoai());

            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialog(list.get(holder.getAdapterPosition()));
                }
            });

            holder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean check = khoanDAO.xoaCapNhat(list.get(holder.getAdapterPosition()).getIdKhoan());
                    if(check){
                        getDS();
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIDKhoan, txtTien, txtNgay,txtKhoan,txtTenLoai;
        ImageView ivEdit, ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIDKhoan = itemView.findViewById(R.id.txtIDKhoan);
            txtTien = itemView.findViewById(R.id.txtTien);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtKhoan = itemView.findViewById(R.id.txtKhoan);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDel = itemView.findViewById(R.id.ivDel);

        }
    }

    private void showDialog(Khoan khoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view =  inflater.inflate(R.layout.khoan_dialog,null);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText edtKhoan = view.findViewById(R.id.edtKhoan);
        EditText edtTien = view.findViewById(R.id.edtTien);
        EditText edtNgay = view.findViewById(R.id.edtNgay);
        Spinner spnLoai = view.findViewById(R.id.spnLoai);
        Button btnThemSua = view.findViewById(R.id.btnThemSua);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        edtKhoan.setText(khoan.getKhoan());
        edtTien.setText(String.valueOf(khoan.getTien()));
        edtNgay.setText(khoan.getNgay());


        TextView txtTieuDe = view.findViewById(R.id.txtTieuDe);
        txtTieuDe.setText("Cập nhật khoản");
        btnThemSua.setText("Cập nhật");

        edtNgay.setFocusable(false);
        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(edtNgay);
            }
        });
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,Object> hashMap = (HashMap<String, Object>) spnLoai.getSelectedItem();
                idLoai= String.valueOf(hashMap.get("idLoai")) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnThemSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenKhoan = edtKhoan.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                String ngay = edtNgay.getText().toString();
                int id =  Integer.parseInt(idLoai); // idLoai kiểu int
                Khoan khoan2 = new Khoan(khoan.getIdKhoan(),tenKhoan,tien,ngay,id);
                boolean check = khoanDAO.capNhapKhoan(khoan2);
                if(check){
                    getDS();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });


        layDSSpinner(spnLoai,khoan.getIdLoai());
    }



    private void showDatePickerDialog(EditText edtNgay) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String thang ="";
                        if(i1<9){
                            thang = "0"+(i1+1);
                        }else{
                            thang=String.valueOf((i1+1));
                        }
                        String ngay="";
                        if(i2<10){
                            ngay = "0"+i2;
                        }else {
                            ngay=String.valueOf(i2);
                        }
                        edtNgay.setText(ngay+"/"+thang+"/"+i);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)


        );
        datePickerDialog.show();
    }

    private void layDSSpinner(Spinner spnLoai,int idLoai) {
        int viTri=-1;
        int position = 0;
        LoaiDAO loaiDAO = new LoaiDAO(context);
        ArrayList<Loai> list = loaiDAO.layDSLoai(trangThai);
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (Loai loai: list ) {
            HashMap<String,Object> hashMap  = new  HashMap<>();
            hashMap.put("idLoai",loai.getIdLoai() );
            hashMap.put("tenLoai",loai.getTenLoai());
            listHM.add(hashMap);
            if(loai.getIdLoai()==idLoai){
                viTri = position;
            }
            position++;
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnLoai.setAdapter(simpleAdapter);
        spnLoai.setSelection(viTri);

    }

    public void getDS() {
        list.clear();
        list=khoanDAO.layDSKhoan(trangThai);
        notifyDataSetChanged();
    }

}
