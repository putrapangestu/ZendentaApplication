package com.example.zendenta.Model;

import java.util.Date;

public class DataKunjungan {
    String nama,dokter,catatan,treatment,pembayaran,status,tanggal;
    Integer pasien_id,harga;

    public DataKunjungan(String nama, String dokter, String catatan, String treatment, String pembayaran, String status, Integer pasien_id, Integer harga, String tanggal) {
        this.nama = nama;
        this.dokter = dokter;
        this.catatan = catatan;
        this.treatment = treatment;
        this.pembayaran = pembayaran;
        this.status = status;
        this.pasien_id = pasien_id;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPasien_id() {
        return pasien_id;
    }

    public void setPasien_id(Integer pasien_id) {
        this.pasien_id = pasien_id;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
