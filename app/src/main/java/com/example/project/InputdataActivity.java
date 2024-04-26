package com.example.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class InputdataActivity extends AppCompatActivity {

    EditText tanggal, nomor, nama, jk, alamat;
    Button simpan;
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        tanggal = findViewById(R.id.tanggal);
        nomor = findViewById(R.id.nomor);
        nama = findViewById(R.id.nama);
        jk = findViewById(R.id.jk);
        alamat = findViewById(R.id.alamat);
        simpan = findViewById(R.id.simpan);
        kembali = findViewById(R.id.back);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputNomor = nomor.getText().toString().trim();
                String inputNama = nama.getText().toString().trim();
                String inputTanggal = tanggal.getText().toString().trim();
                String inputJK = jk.getText().toString().trim();
                String inputAlamat = alamat.getText().toString().trim();

                if (inputNomor.isEmpty() || inputNama.isEmpty() || inputTanggal.isEmpty() || inputJK.isEmpty() || inputAlamat.isEmpty()) {
                    Toast.makeText(InputdataActivity.this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
                } else {
                    boolean success = simpanData(inputNomor, inputNama, inputTanggal, inputJK, inputAlamat);
                    if (success) {
                        Toast.makeText(InputdataActivity.this, "Data Mahasiswa berhasil disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InputdataActivity.this, LihatdataActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(InputdataActivity.this, "Gagal menyimpan data mahasiswa ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void showDatePickerDialog(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = String.format("%02d-%02d-%d", dayOfMonth, (month + 1), year);
                tanggal.setText(selectedDate);
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private boolean simpanData(String nomor, String nama, String tanggal, String jk, String alamat) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.insertMahasiswaData(nomor, nama, tanggal, jk, alamat);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}