package com.example.qlnv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlnv.model.NhanVien;
import com.example.qlnv.model.Phong;

import java.util.ArrayList;

public class EditNhanVien extends AppCompatActivity {
    DatabaseHelper db;
    int gioiTinh, phongId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nhan_vien);

        db = new DatabaseHelper(this);

        final EditText edTen, edTuoi, edChuyenMon;
        Spinner spinnerGioiTinh, spinnerPhong;
        Button btnEdit = findViewById(R.id.btnEdit);

        edTen = findViewById(R.id.edTen);
        edTuoi = findViewById(R.id.edTuoi);
        edChuyenMon = findViewById(R.id.edChuyenMon);
        spinnerGioiTinh = findViewById(R.id.spinnerGioiTinh);
        spinnerPhong = findViewById(R.id.spinnerPhong);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        NhanVien nhanVien = db.getNhanVienById(id);

        edTen.setText(nhanVien.getTen());
        edTuoi.setText(String.valueOf(nhanVien.getTuoi()));
        edChuyenMon.setText(nhanVien.getChuyenMon());

        final String[] listGioiTinh = {"Nam", "Nu"};
        ArrayAdapter<String> adapterGioiTinh = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listGioiTinh);
        spinnerGioiTinh.setAdapter(adapterGioiTinh);

        spinnerGioiTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(listGioiTinh[i].equals("Nam"))
                    gioiTinh = 1;
                else
                    gioiTinh = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<Phong> listPhong = db.getListPhong();
        final ArrayList<String> listTenPhong = new ArrayList<>();
        final ArrayList<Integer> listIdPhong = new ArrayList<>();

        for (int i=0;i<listPhong.size();i++){
            Phong phong = listPhong.get(i);
            listIdPhong.add(phong.getId());
            listTenPhong.add(phong.getTen());
        }

        ArrayAdapter adapterPhong = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTenPhong);

        spinnerPhong.setAdapter(adapterPhong);
        spinnerPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phongId = listIdPhong.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nhanVien = new NhanVien(edTen.getText().toString(), Integer.parseInt(edTuoi.getText().toString()), gioiTinh, edChuyenMon.getText().toString(), phongId);
                db.editNhanVien(nhanVien);
                Toast.makeText(getBaseContext(), "SUA NHAN VIEN THANH CONG", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        });
    }
}
