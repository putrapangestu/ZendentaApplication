package com.example.zendenta.Data;

import com.example.zendenta.Model.TreatmentModel;

import java.util.ArrayList;

public class Treatment {
    public static ArrayList<TreatmentModel> getTreatment(){
        fillData();
        return listTreat;
    }

    public static ArrayList<TreatmentModel> listTreat = new ArrayList<>();
    public static void fillData(){
        listTreat.clear();
        listTreat.add(new TreatmentModel("Pemutihan Gigi"));
        listTreat.add(new TreatmentModel("Ronsen Gigi"));
        listTreat.add(new TreatmentModel("Pencabutan Gigi"));
        listTreat.add(new TreatmentModel("Pemasangan Behel"));
        listTreat.add(new TreatmentModel("Penambalan Gigi"));
        listTreat.add(new TreatmentModel("Pasang Gigi Palsu"));
    }
}
