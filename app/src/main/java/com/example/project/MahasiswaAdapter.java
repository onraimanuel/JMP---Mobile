package com.example.project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {

    private List<String> daftarNamaMahasiswa; // Change to List<String> for storing names

    public MahasiswaAdapter(List<String> daftarNamaMahasiswa) {
        this.daftarNamaMahasiswa = daftarNamaMahasiswa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String namaMahasiswa = daftarNamaMahasiswa.get(position);
        holder.textViewNama.setText(namaMahasiswa);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view, namaMahasiswa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return daftarNamaMahasiswa.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNama;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNama = itemView.findViewById(R.id.textViewNama);
        }
    }

//    private void showPopupMenu(View view, String nama) {
//        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
//        popupMenu.inflate(R.menu.menu_mahasiswa);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                int itemId = menuItem.getItemId();
//                if (itemId == R.id.lihat_data) {
//                    DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
//                    Cursor cursor = dbHelper.getMahasiswaDetail(nama);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        String nomor = cursor.getString(cursor.getColumnIndex("nomor"));
//                        String tanggalLahir = cursor.getString(cursor.getColumnIndex("tanggal_lahir"));
//                        String jenisKelamin = cursor.getString(cursor.getColumnIndex("jenis_kelamin"));
//                        String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
//                        detailMahasiswa(view.getContext(), nomor, nama, tanggalLahir, jenisKelamin, alamat);
//                        cursor.close();
//                    }
//                    return true;
//                } else if (itemId == R.id.update_data) {
//                    DatabaseHelper dbHelper = new DatabaseHelper(view.getContext());
//                    Cursor cursor = dbHelper.getMahasiswaDetail(nama);
//                    if (cursor != null && cursor.moveToFirst()) {
//                        String nomor = cursor.getString(cursor.getColumnIndex("nomor"));
//                        String tanggalLahir = cursor.getString(cursor.getColumnIndex("tanggal_lahir"));
//                        String jenisKelamin = cursor.getString(cursor.getColumnIndex("jenis_kelamin"));
//                        String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
//                        updateMahasiswa(view.getContext(), nomor, nama, tanggalLahir, jenisKelamin, alamat);
//                        cursor.close();
//                    }
//                    return true;
//                } else if (itemId == R.id.hapus_data) {
//                    deleteDataFromDatabase(view.getContext(), nama);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        popupMenu.show();
//    }

    private void showPopupMenu(View view, String nama) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Pilihan");
        String[] options = {"Lihat Data", "Update Data", "Hapus Data"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (id == 0) {
                    lihatData(view.getContext(), nama);
                } else if (id == 1) {
                    updateData(view.getContext(),nama);
                } else if (id == 2) {
                    deleteData(view.getContext(), nama);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void lihatData(Context context, String nama){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        Cursor cursor = dbHelper.getMahasiswaDetail(nama);
        if (cursor != null && cursor.moveToFirst()) {
            String nomor = cursor.getString(cursor.getColumnIndex("nomor"));
            String tanggalLahir = cursor.getString(cursor.getColumnIndex("tanggal_lahir"));
            String jenisKelamin = cursor.getString(cursor.getColumnIndex("jenis_kelamin"));
            String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
            detailMahasiswa(context, nomor, nama, tanggalLahir, jenisKelamin, alamat);
            cursor.close();
        }
    }
    private void updateData(Context context, String nama){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        Cursor cursor = dbHelper.getMahasiswaDetail(nama);
        if (cursor != null && cursor.moveToFirst()) {
            String nomor = cursor.getString(cursor.getColumnIndex("nomor"));
            String tanggalLahir = cursor.getString(cursor.getColumnIndex("tanggal_lahir"));
            String jenisKelamin = cursor.getString(cursor.getColumnIndex("jenis_kelamin"));
            String alamat = cursor.getString(cursor.getColumnIndex("alamat"));
            updateMahasiswa(context, nomor, nama, tanggalLahir, jenisKelamin, alamat);
            cursor.close();
        }
    }

    private void deleteData(Context context, String nama) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean isDeleted = dbHelper.deleteMahasiswa(nama);

        if (isDeleted) {
            int removedIndex = daftarNamaMahasiswa.indexOf(nama);
            if (removedIndex != -1) {
                removeItem(removedIndex);
            }
            Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

            if (daftarNamaMahasiswa.isEmpty()) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((MainActivity) context).finish();
            }
        } else {
            Toast.makeText(context, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
        }
    }
    private void detailMahasiswa (Context context, String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        Intent intent = new Intent(context, DetailmahasiswaActivity.class);
        intent.putExtra("nomor", nomor);
        intent.putExtra("nama", nama);
        intent.putExtra("tanggal_lahir", tanggalLahir);
        intent.putExtra("jenis_kelamin", jenisKelamin);
        intent.putExtra("alamat", alamat);
        context.startActivity(intent);
    }
    public void updateMahasiswa(Context context, String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        Intent intent = new Intent(context, UpdateMahasiswaActivity.class);
        intent.putExtra("nomor", nomor);
        intent.putExtra("nama", nama);
        intent.putExtra("tanggal_lahir", tanggalLahir);
        intent.putExtra("jenis_kelamin", jenisKelamin);
        intent.putExtra("alamat", alamat);
        context.startActivity(intent);
    }
    public void removeItem(int position) {
        daftarNamaMahasiswa.remove(position);
        notifyItemRemoved(position);
    }
}
