package com.example.zendenta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendenta.Model.DokterModel;
import com.example.zendenta.R;

import java.util.ArrayList;

class dokterHolder extends RecyclerView.ViewHolder{
    static TextView id;
    static TextView nama;
    static TextView harga;
    static ImageView gambar;
    static CardView card;
    public dokterHolder(@NonNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.cardDokter);
        id = itemView.findViewById(R.id.idDokter);
        nama = itemView.findViewById(R.id.DokterList);
        harga = itemView.findViewById(R.id.hargaList);
        gambar = itemView.findViewById(R.id.gambarDokter);
    }
}



public class DokterAdapter extends RecyclerView.Adapter<dokterHolder> {
    public static ClickListener clickListener;
    Context context;
    ArrayList<DokterModel> listDokter;

    public DokterAdapter(Context context, ArrayList<DokterModel> listDokter, ClickListener clickListener) {
        this.context = context;
        this.listDokter = listDokter;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public dokterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dokter,parent,false);
        return new dokterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dokterHolder holder, int position) {
        DokterModel dokter = listDokter.get(position);
        dokterHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(listDokter.get(position));
            }
        });
        dokterHolder.id.setText(dokter.getId());
        dokterHolder.nama.setText(dokter.getNama());
        dokterHolder.harga.setText(dokter.getHarga());
        dokterHolder.gambar.setImageResource(dokter.getGambar());
    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

//    public void setOnItemClickListener(ClickListener cLickListener) {
//        DokterAdapter.clickListener = clickListener;
//    }
}
