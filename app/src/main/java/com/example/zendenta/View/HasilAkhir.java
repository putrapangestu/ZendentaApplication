package com.example.zendenta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Header;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.model.Headers;
import com.example.zendenta.Model.DataUser;
import com.example.zendenta.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HasilAkhir extends AppCompatActivity {
    ProgressBar progressBar;
    CardView btnKonfirmasi;
    TextView dokter,metode,nama,harga,tanggal,total;
    ImageView gambar;

    DataUser user = Profile.user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_akhir);

        sharedPreferences = getSharedPreferences("dataKunjungan",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        gambar = findViewById(R.id.gambarDokter);
        dokter = findViewById(R.id.DokterList);
        metode = findViewById(R.id.metodePasien);
        nama = findViewById(R.id.namaPasien);
        harga = findViewById(R.id.hargaList);
        total = findViewById(R.id.totalHarga);
        tanggal = findViewById(R.id.tanggalPasien);

        if(sharedPreferences.contains("dokterId")){
            dataKunjungan();
        }

        Activity();
    }

    private void Activity(){
        btnKonfirmasi = findViewById(R.id.buttonKonfirmasi);
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });
    }

    private void dataKunjungan(){
        gambar.setImageResource(sharedPreferences.getInt("gambar",0));
        dokter.setText(sharedPreferences.getString("nama",""));
        metode.setText(sharedPreferences.getString("treatment",""));
        nama.setText(user.getNama());
        harga.setText(sharedPreferences.getString("harga",""));
        total.setText(sharedPreferences.getString("harga",""));
        tanggal.setText(sharedPreferences.getString("tanggal",""));
    }

//    private void postData(){
//        final ProgressDialog loading = new ProgressDialog(HasilAkhir.this);
//        loading.setMessage("Tunggu Sebentar.....");
//        loading.setCanceledOnTouchOutside(false);
//        loading.show();
//
//        //make data to post
//        JSONObject jsonObject = new JSONObject();
//        try{
//            String tgl = sharedPreferences.getString("tanggal","");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date tglKunjungan = sdf.parse(tgl);
//            jsonObject.put("pasien_id",Integer.parseInt(user.getId()));
//            jsonObject.put("treatment",sharedPreferences.getString("treatent",""));
//            jsonObject.put("catatan","saya ingin melakukan pemeriksaan.");
//            jsonObject.put("dokter_id",sharedPreferences.getInt("dokterId",0));
//            jsonObject.put("tanggal",tglKunjungan);
//            jsonObject.put("status","pending");
//            jsonObject.put("pembayaran","belum");
//        }catch (JSONException e){
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        //make query post
//        String URL = "http://192.168.1.201/api/kunjungan";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(HasilAkhir.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
//                try {
//                    Log.d("JSON", String.valueOf(response));
//                    loading.dismiss();
//                    String Error = response.getString("httpStatus");
//                    if (Error.equals("")||Error.equals(null)){
//
//                    }else if(Error.equals("OK")){
//                        JSONObject body = response.getJSONObject("body");
//
//                    }else {
////                        editor.clear();
////                        editor.commit();
////                        startActivity(new Intent(HasilAkhir.this, Home.class));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    loading.dismiss();
//                }
////                        resultTextView.setText("String Response : "+ response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
//                VolleyLog.d("Error", "Error: " + error.getMessage());
//                Toast.makeText(HasilAkhir.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(jsonObjectRequest);
//    }

    private void postData(){
        final ProgressDialog loading = new ProgressDialog(HasilAkhir.this);
        loading.setMessage("Tunggu Sebentar.....");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        RequestQueue queue = Volley.newRequestQueue(HasilAkhir.this);
        String URL = "http://192.168.1.201/api/kunjungan";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                editor.clear();
                editor.commit();
                startActivity(new Intent(HasilAkhir.this, Home.class));
                Toast.makeText(getApplicationContext(),"Berhasil membuat, silahkan tunggu persetujuan",Toast.LENGTH_LONG).show();
//                Log.e("RESPONSE",response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    // on below line we are displaying a success toast message.
//                    Toast.makeText(HasilAkhir.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        // Show timeout error message
                        Toast.makeText(getApplicationContext(),
                                "Oops. Timeout error!",
                                Toast.LENGTH_LONG).show();
                    }
                }
                Log.e("Tag Error", String.valueOf(error));
                    loading.dismiss();
//
//                Toast.makeText(getApplicationContext(),statusCode,Toast.LENGTH_SHORT).show();
//
//
            }
        })
        {
        @Override
        public String getBodyContentType() {
            // as we are passing data in the form of url encoded
            // so we are passing the content type below
            return "application/x-www-form-urlencoded; charset=UTF-8";
        }

            @Override
            protected  Map<String ,String> getParams()
            {
                Map<String, String> params = new HashMap<String,String>();
                String tgl = sharedPreferences.getString("tanggal","");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date tglKunjungan = null;
                try {
                    tglKunjungan = sdf.parse(tgl);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                Integer dokter_id = sharedPreferences.getInt("dokter_id",0);
                params.put("pasien_id",user.getId());
                params.put("treatment",sharedPreferences.getString("treatment",""));
                params.put("catatan","saya ingin melakukan pemeriksaan.");
                params.put("dokter_id",sharedPreferences.getString("dokterId",""));
                params.put("tanggal",sharedPreferences.getString("tanggal",""));
                params.put("status","pending");
                params.put("pembayaran","belum");
                Log.d("DATA",String.valueOf(params));
                return params;

            }

//            @Override
//            public Map<String,String> getHeaders() throws AuthFailureError{
//                Map<String,String> params = new HashMap<String,String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
        };

//        request.setRetryPolicy(new DefaultRetryPolicy(
//                5000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }
}