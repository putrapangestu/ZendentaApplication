package com.example.zendenta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.zendenta.Model.DataUser;
import com.example.zendenta.View.Home;
import com.example.zendenta.View.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText username,password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("myDataUser",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.contains("username"))
        {
            startActivity(new Intent(MainActivity.this, Home.class));
            DataUser user = new DataUser(sharedPreferences.getString("nama",""),sharedPreferences.getString("username",""),sharedPreferences.getString("alamat",""),
                    sharedPreferences.getString("jenisKelamin",""),sharedPreferences.getString("id",""),sharedPreferences.getString("umur",""),sharedPreferences.getString("tanggal",""));
            Profile.user = user;
        }

        btnSubmit = findViewById(R.id.btnSubmit);
//        btnSubmit.setOnClickListener(getData());
//        btnSubmit.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, Home.class)));
        btnSubmit.setOnClickListener(view -> {
            getData();
        });
    }

    private void getData()
    {
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        String URL = "http://192.168.1.201/api/pasien?username=" + username.getText();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest requestString = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE",String.valueOf(response));
                DataUser user;
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() != 0 && password.getText().toString().equals("zendenta") || response.equals("404")){
                        JSONObject data = jsonArray.getJSONObject(0);
                        String[] tglLahir = data.getString("TanggalLahir").split("T");
                        user = new DataUser(data.getString("Nama"),data.getString("Username"),data.getString("Alamat"),data.getString("JenisKelamin"),
                                data.getString("Id"), data.getString("Umur"),tglLahir[0]);
                        Profile.user = user;
                        editor.putString("id",data.getString("Id"));
                        editor.putString("nama",data.getString("Nama"));
                        editor.putString("username",data.getString("Username"));
                        editor.putString("tanggal",tglLahir[0]);
                        editor.putString("alamat",data.getString("Alamat"));
                        editor.putString("umur",data.getString("Umur"));
                        editor.putString("jenisKelamin",data.getString("JenisKelamin"));
                        editor.commit();
                        startActivity(new Intent(MainActivity.this, Home.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Password salah, silahkan cek ulang",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Data tidak ditemukan, silahkan daftar ke admin terlebih dahulu",Toast.LENGTH_LONG).show();
                Log.d("ERROR",String.valueOf(error));
            }
        });
        queue.add(requestString);
    }
}