package com.example.zendenta.Data;

import com.example.zendenta.Model.KunjunganModel;
import com.example.zendenta.R;

import java.util.ArrayList;

public class Kunjungan {
    public static ArrayList<KunjunganModel> getKunjungan(){
        fillData();
        return listKunjungan;
    }
    public static ArrayList<KunjunganModel> listKunjungan = new ArrayList<>();
    public static void fillData(){
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
        listKunjungan.add(new KunjunganModel("drg.Nunuk Setiyawan, Sp.BM","22-09-2012","Pencabutan Gigi","100000", R.drawable.tes_logo_2));
    }
}
