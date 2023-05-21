package com.example.zendenta.Model;

import java.util.Date;

public class DataUser {
    String nama,username,alamat,jk,tanggal,id,umur;

    public DataUser(String nama, String username, String alamat, String jk, String id, String umur, String tanggal) {
        this.nama = nama;
        this.username = username;
        this.alamat = alamat;
        this.jk = jk;
        this.id = id;
        this.umur = umur;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
