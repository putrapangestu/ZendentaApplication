package com.example.zendenta.Model;

public class KunjunganModel {
    String nama,tanggal,metode,harga;
    Integer gambar;

    public KunjunganModel(String nama, String tanggal, String metode, String harga, Integer gambar) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.metode = metode;
        this.harga = harga;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMetode() {
        return metode;
    }

    public void setMetode(String metode) {
        this.metode = metode;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public Integer getGambar() {
        return gambar;
    }

    public void setGambar(Integer gambar) {
        this.gambar = gambar;
    }
}
