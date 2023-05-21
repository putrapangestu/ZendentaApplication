package com.example.zendenta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendenta.Model.KunjunganModel;
import com.example.zendenta.R;

import java.util.ArrayList;

class kunjunganHolder extends RecyclerView.ViewHolder{
    static TextView nama;
    static TextView tanggal;
    static TextView metode;
    static TextView harga;
    static ImageView gambar;
    public kunjunganHolder(@NonNull View itemView) {
        super(itemView);
        nama = itemView.findViewById(R.id.DokterPasien);
        tanggal = itemView.findViewById(R.id.tanggalKunjungan);
        metode = itemView.findViewById(R.id.metodeKunjungan);
        harga = itemView.findViewById(R.id.hargaKunjungan);
        gambar = itemView.findViewById(R.id.gambarDokter);
    }
}

public class KunjunganAdapter extends RecyclerView.Adapter<kunjunganHolder> {
    Context context;
    ArrayList<KunjunganModel> listKunjungan;

    public KunjunganAdapter(Context context, ArrayList<KunjunganModel> listKunjungan) {
        this.context = context;
        this.listKunjungan = listKunjungan;
    }

    @NonNull
    @Override
    public kunjunganHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_kunjungan,parent,false);
        return new kunjunganHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull kunjunganHolder holder, int position) {
        KunjunganModel kunjungan = listKunjungan.get(position);
        kunjunganHolder.nama.setText(kunjungan.getNama());
        kunjunganHolder.tanggal.setText(kunjungan.getTanggal());
        kunjunganHolder.harga.setText(kunjungan.getHarga());
        kunjunganHolder.metode.setText(kunjungan.getMetode());
        kunjunganHolder.gambar.setImageResource(kunjungan.getGambar());
    }

    @Override
    public int getItemCount() {
        return listKunjungan.size();
    }
}
