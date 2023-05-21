package com.example.zendenta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendenta.Model.DataHistory;
import com.example.zendenta.Model.KunjunganModel;
import com.example.zendenta.R;

import java.util.ArrayList;

class historyHolder extends RecyclerView.ViewHolder{
    static TextView nama;
    static TextView tanggal;
    static TextView metode;
    static TextView harga;
    static ImageView gambar;
    public historyHolder(@NonNull View itemView) {
        super(itemView);
        nama = itemView.findViewById(R.id.DokterPasien);
        tanggal = itemView.findViewById(R.id.tanggalKunjungan);
        metode = itemView.findViewById(R.id.metodeKunjungan);
        harga = itemView.findViewById(R.id.hargaKunjungan);
        gambar = itemView.findViewById(R.id.gambarDokter);
    }
}

public class HistoryAdapter extends RecyclerView.Adapter<historyHolder> {
    Context context;
    ArrayList<DataHistory> listHistory;

    public HistoryAdapter(Context context, ArrayList<DataHistory> listHistory) {
        this.context = context;
        this.listHistory = listHistory;
    }

    @NonNull
    @Override
    public historyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_kunjungan,parent,false);
        return new historyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyHolder holder, int position) {
        DataHistory kunjungan = listHistory.get(position);
        historyHolder.nama.setText(kunjungan.getNama());
        historyHolder.tanggal.setText(kunjungan.getTanggal());
        historyHolder.harga.setText(kunjungan.getHarga());
        historyHolder.metode.setText(kunjungan.getMetode());
        historyHolder.gambar.setImageResource(kunjungan.getGambar());
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }
}
