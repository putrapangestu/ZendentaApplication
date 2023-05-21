package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendenta.Adapter.ClickListener;
import com.example.zendenta.Adapter.DokterAdapter;
import com.example.zendenta.Data.Dokter;
import com.example.zendenta.Model.DokterModel;
import com.example.zendenta.R;

import java.util.ArrayList;

public class PilihDokter extends AppCompatActivity implements ClickListener {
    CardView btnNext,btnBack;
    TextView nama,harga;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    RecyclerView rvView;
    LinearLayoutManager linearManager;
    DokterAdapter adapter;
    ArrayList<DokterModel> listDokter = Dokter.getDokter();

    void Data()
    {
        rvView = findViewById(R.id.rvDokter);
        linearManager = new LinearLayoutManager(this);
        adapter = new DokterAdapter(this,this.listDokter,this);
        rvView.setLayoutManager(linearManager);
        rvView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_dokter);

        sharedPreferences = getSharedPreferences("dataKunjungan",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Data();
        Activity();
    }

    private void Activity(){
        //slide activity
        btnBack = findViewById(R.id.cardView2);
        btnBack.setOnClickListener(view -> super.onBackPressed());
        btnNext = findViewById(R.id.buttonNextTreat);
        nama = findViewById(R.id.namaDokter);
        harga = findViewById(R.id.hargaDokter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.getText().equals("") || harga.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"Silahkan pilih data terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(PilihDokter.this,PilihTreatment.class));
                }
            }
        });

    }

    @Override
    public void onItemClick(DokterModel dokterModel) {

        editor.putString("dokterId",dokterModel.getId());
        Log.d("DATA",dokterModel.getId());
        editor.putString("nama",dokterModel.getNama());
        editor.putString("harga",dokterModel.getHarga());
        editor.putInt("gambar",dokterModel.getGambar());
        editor.commit();

        nama = findViewById(R.id.namaDokter);
        nama.setText(dokterModel.getNama());
        harga = findViewById(R.id.hargaDokter);
        harga.setText(dokterModel.getHarga());

//        Toast.makeText(this,dokterModel.getId(),Toast.LENGTH_SHORT).show();
    }
}