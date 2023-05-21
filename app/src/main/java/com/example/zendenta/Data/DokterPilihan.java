package com.example.zendenta.Data;

public class DokterPilihan {
    String nama, harga, id;
    Integer gambar;

    public DokterPilihan(String nama, String harga, String id, Integer gambar) {
        this.nama = nama;
        this.harga = harga;
        this.id = id;
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGambar() {
        return gambar;
    }

    public void setGambar(Integer gambar) {
        this.gambar = gambar;
    }
}
