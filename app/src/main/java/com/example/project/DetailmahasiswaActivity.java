package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;


public class DetailmahasiswaActivity extends AppCompatActivity {
    EditText nomorEditText, namaEditText, tanggalEditText, jkEditText, alamatEditText;
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailmahasiswa);

        nomorEditText = findViewById(R.id.nomor);
        namaEditText = findViewById(R.id.nama);
        tanggalEditText = findViewById(R.id.tanggal);
        jkEditText = findViewById(R.id.jk);
        alamatEditText = findViewById(R.id.alamat);
        kembali = findViewById(R.id.backdet);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nomor = extras.getString("nomor");
            String nama = extras.getString("nama");
            String tanggalLahir = extras.getString("tanggal_lahir");
            String jenisKelamin = extras.getString("jenis_kelamin");
            String alamat = extras.getString("alamat");

            nomorEditText.setText(nomor);
            namaEditText.setText(nama);
            tanggalEditText.setText(tanggalLahir);
            jkEditText.setText(jenisKelamin);
            alamatEditText.setText(alamat);
        }

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}