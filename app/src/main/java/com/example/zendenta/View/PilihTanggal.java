package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zendenta.R;

import java.util.Calendar;

public class PilihTanggal extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    CardView btnBack,btnNext;
    TextView tanggal;
    DatePicker kalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tanggal);

        sharedPreferences = getSharedPreferences("dataKunjungan",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        pickTanggal();
        Activity();

    }

    private void Activity(){
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view->super.onBackPressed());
        btnNext = findViewById(R.id.buttonLast);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tanggal.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"Silahkan pilih data terlebih dahulu",Toast.LENGTH_SHORT).show();
                }else{
                    editor.putString("tanggal",tanggal.getText().toString());
                    editor.commit();
                    startActivity(new Intent(PilihTanggal.this,HasilAkhir.class));
                }
            }
        });
    }

    private void pickTanggal(){
        tanggal = findViewById(R.id.tanggalPick);
//        Tanggal();
        kalender = findViewById(R.id.datePicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            kalender.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    int month = i1+1;
                    tanggal.setText(i+"-"+month+"-"+i2);
                }
            });
        }
    }

}