package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zendenta.Adapter.HistoryAdapter;
import com.example.zendenta.MainActivity;
import com.example.zendenta.Model.DataHistory;
import com.example.zendenta.Model.DataUser;
import com.example.zendenta.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    CardView btnTambah,btnLengkap,cardKunjungan;
    ImageView homePage,kunjunganPage,historyPage,profilePage,logout;
    TextView tanggal,dokter,metode;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getData();
        Activity();
    }

    private void Activity(){
        //slide activity
        btnTambah = findViewById(R.id.tambahKunjungan);
        btnTambah.setOnClickListener(view->startActivity(new Intent(Home.this, PilihDokter.class)));
        homePage = findViewById(R.id.homePage);
        homePage.setOnClickListener(view->startActivity(new Intent(Home.this,Home.class)));
        kunjunganPage = findViewById(R.id.kunjunganPage);
        kunjunganPage.setOnClickListener(view->startActivity(new Intent(Home.this,KunjunganPage.class)));
        historyPage = findViewById(R.id.historyPage);
        historyPage.setOnClickListener(view->startActivity(new Intent(Home.this,History.class)));
        profilePage = findViewById(R.id.profilePage);
        profilePage.setOnClickListener(view->startActivity(new Intent(Home.this,Profile.class)));
        btnLengkap = findViewById(R.id.btnSelengkapnya);
        btnLengkap.setOnClickListener(view -> startActivity(new Intent(Home.this, History.class)));

        sharedPreferences = getSharedPreferences("myDataUser",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData()
    {
        tanggal = findViewById(R.id.tanggal);
        dokter = findViewById(R.id.dokter);
        metode = findViewById(R.id.metode);
        DataUser user = Profile.user;
        String URL = "http://192.168.1.201/api/kunjungan?id="+ user.getId() + "&status=diterima&pembayaran=belum";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<DataHistory> listHistory = new ArrayList<>();
                try{
                    JSONArray jsonArray =  new JSONArray(response);
                    if(jsonArray.length() != 0){
                        JSONObject data = jsonArray.getJSONObject(0);
                        String[] tanggals = data.getString("Tanggal").split("T");
                        tanggal.setText(tanggals[0]);
                        dokter.setText(data.getString("Dokter"));
                        metode.setText(data.getString("Treatment"));
                    } else {
                        btnLengkap = findViewById(R.id.btnSelengkapnya);
                        btnLengkap.setVisibility(View.GONE);
                        cardKunjungan = findViewById(R.id.cardKunjungan);
                        cardKunjungan.setVisibility(View.GONE);
                    }

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