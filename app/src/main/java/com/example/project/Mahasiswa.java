package com.example.project;

public class Mahasiswa {
    private String nomor;
    private String nama;
    private String tanggalLahir;
    private String jenisKelamin;
    private String alamat;

    public Mahasiswa(String nomor, String nama, String tanggalLahir, String jenisKelamin, String alamat) {
        this.nomor = nomor;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public String getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }
}
