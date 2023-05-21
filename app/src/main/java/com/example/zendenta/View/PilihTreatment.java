package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendenta.Adapter.ClickTreatment;
import com.example.zendenta.Adapter.DokterAdapter;
import com.example.zendenta.Adapter.TreatmentAdapter;
import com.example.zendenta.Data.Dokter;
import com.example.zendenta.Data.Treatment;
import com.example.zendenta.Model.DokterModel;
import com.example.zendenta.Model.TreatmentModel;
import com.example.zendenta.R;

import java.util.ArrayList;

public class PilihTreatment extends AppCompatActivity implements ClickTreatment{

    CardView btnBack,btnNext;
    TextView treat;

    ClickTreatment clickTreatment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    RecyclerView rvView;
    LinearLayoutManager linearManager;
    TreatmentAdapter adapter;
    ArrayList<TreatmentModel> listTreat = Treatment.getTreatment();

    void Data()
    {
        rvView = findViewById(R.id.rvTreat);
        linearManager = new LinearLayoutManager(this);
        adapter = new TreatmentAdapter(this,this.listTreat, (ClickTreatment) this);
        rvView.setLayoutManager(linearManager);
        rvView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_treatment);

        sharedPreferences = getSharedPreferences("dataKunjungan",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Data();
        Activity();
    }

    private void Activity(){
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view -> super.onBackPressed());
        btnNext = findViewById(R.id.buttonNextTanggal);
        treat = findViewById(R.id.pilihanTreatment);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(treat.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"Silahkan pilih data terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("treatment",treat.getText().toString());
                    editor.commit();
                    startActivity(new Intent(PilihTreatment.this,PilihTanggal.class));
                }
            }
        });
    }

    @Override
    public void onItemClick(TreatmentModel treatmentModel) {
        treat = findViewById(R.id.pilihanTreatment);
        treat.setText(treatmentModel.getNama());
    }
}