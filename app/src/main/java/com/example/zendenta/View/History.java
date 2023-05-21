package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zendenta.Adapter.HistoryAdapter;
import com.example.zendenta.Adapter.KunjunganAdapter;
import com.example.zendenta.Data.Kunjungan;
import com.example.zendenta.Model.DataHistory;
import com.example.zendenta.Model.DataUser;
import com.example.zendenta.Model.KunjunganModel;
import com.example.zendenta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    CardView btnBack;

    RecyclerView rvView;
    LinearLayoutManager linearManager;
    HistoryAdapter adapter;
    ArrayList<KunjunganModel> listKunjungan = Kunjungan.getKunjungan();

    void Data(){
        rvView = findViewById(R.id.rvHistory);
        linearManager = new LinearLayoutManager(this);
//        adapter = new KunjunganAdapter(this,this.listKunjungan);
        rvView.setLayoutManager(linearManager);
        rvView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getData();
        Activity();
    }

    private void Activity()
    {
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(view->super.onBackPressed());
    }

    private void getData()
    {
        DataUser user = Profile.user;
        String URL = "http://192.168.1.201/api/kunjungan?id="+ user.getId() + "&status=diterima&pembayaran=selesai";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DataHistory> listHistory = new ArrayList<>();
                try{
                    JSONArray jsonArray =  new JSONArray(response);
                    for (int i=0; i< jsonArray.length(); i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        String[] tanggal = data.getString("Tanggal").split("T");
                        listHistory.add(new DataHistory(data.getString("Nama"),tanggal[0],data.getString("Treatment"),data.getString("Harga"),R.drawable.tes_logo_2));
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
                rvView = findViewById(R.id.rvHistory);
                linearManager = new LinearLayoutManager(History.this, LinearLayoutManager.VERTICAL,false);
                rvView.setLayoutManager(linearManager);

                adapter = new HistoryAdapter(History.this,listHistory);
                rvView.setAdapter(adapter);
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