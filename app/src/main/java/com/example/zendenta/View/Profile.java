package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zendenta.Adapter.HistoryAdapter;
import com.example.zendenta.Model.DataHistory;
import com.example.zendenta.Model.DataUser;
import com.example.zendenta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    CardView btnBack;
    TextView nama,alamat,tanggal,jenis,umur,totalRequest,totalKunjungan;
    public static DataUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getData();
        countRequest();
        countKunjungan();

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view->super.onBackPressed());
    }

    private void getData(){
        nama = findViewById(R.id.namaPasien);
        alamat = findViewById(R.id.alamatPasien);
        tanggal = findViewById(R.id.tanggalPasien);
        jenis = findViewById(R.id.jenisPasien);
        umur = findViewById(R.id.umurPasien);


        nama.setText(user.getNama());
        alamat.setText(user.getAlamat());
        tanggal.setText(user.getTanggal());
        jenis.setText(user.getJk());
        umur.setText(user.getUmur());
    }

    private void countRequest(){
        String URL = "http://192.168.1.201/api/kunjungan?id="+ user.getId() + "&status=pending&pembayaran=belum";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DataHistory> listHistory = new ArrayList<>();
                try{
                    JSONArray jsonArray =  new JSONArray(response);
                    totalRequest = findViewById(R.id.totalRequest);
                    Integer total = jsonArray.length();
                    totalRequest.setText(total.toString());
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    private void countKunjungan(){
        String URL = "http://192.168.1.201/api/kunjungan?id="+ user.getId() + "&status=diterima&pembayaran=selesai";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DataHistory> listHistory = new ArrayList<>();
                try{
                    JSONArray jsonArray =  new JSONArray(response);
                    totalKunjungan = findViewById(R.id.totalKunjungan);
                    Integer total = jsonArray.length();
                    totalKunjungan.setText(total.toString());
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

}