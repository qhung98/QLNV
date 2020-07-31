package com.example.qlnv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlnv.model.NhanVien;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<NhanVien> {
    Context context;
    ArrayList<NhanVien> list;
    DatabaseHelper db;

    public Adapter(@NonNull Context context, ArrayList<NhanVien> list) {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        db = new DatabaseHelper(context);

        TextView tvTen, tvTuoi, tvGioiTinh, tvChuyenMon, tvPhong;
        Button btnEdit, btnDelete;

        tvTen = view.findViewById(R.id.tvTen);
        tvTuoi = view.findViewById(R.id.tvTuoi);
        tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        tvChuyenMon = view.findViewById(R.id.tvChuyenMon);
        tvPhong = view.findViewById(R.id.tvPhong);

        btnEdit = view.findViewById(R.id.btnEdit);
        btnDelete = view.findViewById(R.id.btnDelete);

        NhanVien nhanVien = list.get(position);

        tvTen.setText("HO TEN:  " + nhanVien.getTen());
        tvTuoi.setText("TUOI:   " + String.valueOf(nhanVien.getTuoi()));

        String gioiTinh;
        if(nhanVien.getGioiTinh()==1)
            gioiTinh="NAM";
        else
            gioiTinh="NU";
        tvGioiTinh.setText("GIOI TINH:  "  + gioiTinh);

        tvChuyenMon.setText("CHUYEN MON:    " + nhanVien.getChuyenMon());

        DatabaseHelper db = new DatabaseHelper(context);
        String phong = db.getTenPhong(nhanVien.getPhongId());
        tvPhong.setText("PHONG: " + phong);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditNhanVien.class);
                int id = list.get(position).getId();
                intent.putExtra("id", id);
                ((Activity)context).startActivityForResult(intent, 2);
            }
        });

        return view;
    }
}
