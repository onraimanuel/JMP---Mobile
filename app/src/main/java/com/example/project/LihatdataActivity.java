package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LihatdataActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMahasiswa;
    private List<String> daftarNamaMahasiswa;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatdata);

        ImageButton buttonBack = findViewById(R.id.buttonBack);
        Button btnInputdata = findViewById(R.id.inputdata);

        recyclerViewMahasiswa = findViewById(R.id.recyclerViewMahasiswa);
        recyclerViewMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new DatabaseHelper(this);
        daftarNamaMahasiswa = new ArrayList<>();

        Cursor cursor = dbHelper.getAllMahasiswaData();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String namaMahasiswa = cursor.getString(cursor.getColumnIndex("nama"));
                daftarNamaMahasiswa.add(namaMahasiswa);
            } while (cursor.moveToNext());
            cursor.close();
        }

        MahasiswaAdapter adapter = new MahasiswaAdapter(daftarNamaMahasiswa);
        recyclerViewMahasiswa.setAdapter(adapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnInputdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LihatdataActivity.this, InputdataActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
