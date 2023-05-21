package com.example.zendenta.Data;

import com.example.zendenta.Model.DokterModel;
import com.example.zendenta.R;

import java.util.ArrayList;

public class Dokter {
    public static ArrayList<DokterModel> getDokter()
    {
        fillData();
        return listDokter;
    }

    public static ArrayList<DokterModel> listDokter = new ArrayList<>();
    public static void fillData()
    {
        listDokter.clear();
        listDokter.add(new DokterModel("1","drg. Nunuk Setiyawan, Sp.BM","100000", R.drawable.nunuk));
        listDokter.add(new DokterModel("2","drg. Hari Purbowisono","50000", R.drawable.tes_foto));
        listDokter.add(new DokterModel("3","drg. Vita Indriyani","50000", R.drawable.vita));
    }
}
