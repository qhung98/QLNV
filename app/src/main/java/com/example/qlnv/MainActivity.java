package com.example.qlnv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlnv.model.NhanVien;
import com.example.qlnv.model.Phong;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Adapter adapter;
    ArrayList<NhanVien> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        Button btnAddPhong, btnAddNhanVien;
        btnAddPhong = findViewById(R.id.btnAddPhong);
        btnAddNhanVien = findViewById(R.id.btnAddNv);
        Spinner spinner = findViewById(R.id.spinner);
        final ListView listView = findViewById(R.id.listView);

        ArrayList<Phong> listPhong = db.getListPhong();
        final ArrayList<String> listTenPhong = new ArrayList<>();
        final ArrayList<Integer> listIdPhong = new ArrayList<>();

        listTenPhong.add("TAT CA");

        for (int i=0;i<listPhong.size();i++){
            Phong phong = listPhong.get(i);
            listIdPhong.add(phong.getId());
            listTenPhong.add(phong.getTen());
        }

        ArrayAdapter spinAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTenPhong);
        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String phong = listTenPhong.get(i);
                if(phong.equals("TAT CA")){
                    list = db.getNhanVien();
                    adapter = new Adapter(MainActivity.this, list);
                    listView.setAdapter(adapter);
                }
                else {
                    int id = listIdPhong.get(i-1);
                    list = db.getNhanVienByPhong(id);
                    adapter = new Adapter(MainActivity.this, list);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phong phong1 = new Phong("KINH DOANH");
                Phong phong2 = new Phong("PHAN MEM");
                Phong phong3 = new Phong("KIEM DINH");
                db.addPhong(phong1);
                db.addPhong(phong2);
                db.addPhong(phong3);
                Toast.makeText(getBaseContext(), "THEM PHONG THANH CONG", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });

        btnAddNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NhanVien nhanVien1 = new NhanVien("ABC", 25, 1, "CNTT", 2);
//                NhanVien nhanVien2 = new NhanVien("DEF", 45, 0, "KE TOAN", 1);
//                NhanVien nhanVien3 = new NhanVien("GHJ", 22, 1, "CNTT", 3 );
//                NhanVien nhanVien4 = new NhanVien("KLM", 33, 0, "MARKETING", 1);
//                NhanVien nhanVien5 = new NhanVien("OPQ", 55, 1, "KE TOAN", 1);
//
//                db.addNhanVien(nhanVien1);
//                db.addNhanVien(nhanVien2);
//                db.addNhanVien(nhanVien3);
//                db.addNhanVien(nhanVien4);
//                db.addNhanVien(nhanVien5);
//
//                Toast.makeText(getBaseContext(), "THEM NHAN VIEN THANH CONG", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//                finish();

                startActivityForResult(new Intent(MainActivity.this, AddNhanVien.class), 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            this.finish();
        }

        if(requestCode==2&&resultCode==RESULT_OK){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            this.finish();
        }
    }
}
