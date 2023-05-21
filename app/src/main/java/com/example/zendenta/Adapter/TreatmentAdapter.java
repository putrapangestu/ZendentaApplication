package com.example.zendenta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendenta.Model.TreatmentModel;
import com.example.zendenta.R;

import java.util.ArrayList;

class treatHolder extends RecyclerView.ViewHolder{
    static TextView nama;
    static CardView card;
    public treatHolder(@NonNull View itemView) {
        super(itemView);

        nama = itemView.findViewById(R.id.listTreatment);
        card = itemView.findViewById(R.id.cardTreatment);
    }
}

public class TreatmentAdapter extends RecyclerView.Adapter<treatHolder> {
    Context context;
    ArrayList<TreatmentModel> listTreat;
    private static ClickTreatment clickTreatment;

    public TreatmentAdapter(Context context, ArrayList<TreatmentModel> listTreat, ClickTreatment clickTreatment) {
        this.context = context;
        this.listTreat = listTreat;
        this.clickTreatment = clickTreatment;
    }

    @NonNull
    @Override
    public treatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_treatment, parent, false);
        return new treatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull treatHolder holder, int position) {
        TreatmentModel treatment = listTreat.get(position);
        treatHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickTreatment.onItemClick(listTreat.get(position));
            }
        });
        treatHolder.nama.setText(treatment.getNama());
    }

    @Override
    public int getItemCount() {
        return listTreat.size();
    }
}
