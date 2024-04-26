package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateMahasiswaActivity extends AppCompatActivity {
    EditText nomorEditText, namaEditText, tanggalEditText, jkEditText, alamatEditText;
    ImageButton kembali;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        nomorEditText = findViewById(R.id.nomor);
        namaEditText = findViewById(R.id.nama);
        tanggalEditText = findViewById(R.id.tanggal);
        jkEditText = findViewById(R.id.jk);
        alamatEditText = findViewById(R.id.alamat);
        kembali = findViewById(R.id.backdet);
        update = findViewById(R.id.update);

        // Retrieve the values passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nomor = extras.getString("nomor");
            String nama = extras.getString("nama");
            String tanggalLahir = extras.getString("tanggal_lahir");
            String jenisKelamin = extras.getString("jenis_kelamin");
            String alamat = extras.getString("alamat");

            // Set the retrieved values to the EditText fields
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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomorBaru = nomorEditText.getText().toString();
                String namaBaru = namaEditText.getText().toString();
                String tanggalLahirBaru = tanggalEditText.getText().toString();
                String jenisKelaminBaru = jkEditText.getText().toString();
                String alamatBaru = alamatEditText.getText().toString();

                DatabaseHelper dbHelper = new DatabaseHelper(UpdateMahasiswaActivity.this);
                boolean isUpdated = dbHelper.updateData(nomorBaru, namaBaru, tanggalLahirBaru, jenisKelaminBaru, alamatBaru);

                if (isUpdated) {
                    Toast.makeText(UpdateMahasiswaActivity.this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateMahasiswaActivity.this, LihatdataActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateMahasiswaActivity.this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}