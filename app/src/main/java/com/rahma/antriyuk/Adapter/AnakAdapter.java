package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.antriyuk.DetailActivity;
import com.rahma.antriyuk.Entity.EHistory;
import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.R;

import java.util.ArrayList;
import java.util.List;

public class AnakAdapter extends RecyclerView.Adapter<AnakAdapter.HistoryViewHolder> {
    private List<MAntrianak> listAnak;
    Context mContext;
    CardView cardView;

    public AnakAdapter(Context context, List<MAntrianak> data_history) {
        this.mContext = context;
        listAnak  = data_history;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        final MAntrianak antrianak  = listAnak.get(position);
        holder.namapasien.setText(antrianak.getNama());
        holder.noantrian.setText(antrianak.getNoAntrian());
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        public final TextView namapasien,noantrian;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            namapasien = itemView.findViewById(R.id.H_namapasien);
            noantrian = itemView.findViewById(R.id.H_noantri);
            cardView = itemView.findViewById(R.id.cvHistory);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Intent i = new Intent(mContext, DetailActivity.class);
                        i.putExtra("id_antri",listAnak.get(position).getId());

                        i.putExtra("nama", listAnak.get(position).getNama());
                        i.putExtra("noantrian", listAnak.get(position).getNoAntrian());
                        i.putExtra("namapoli", listAnak.get(position).getNamaPoli());
                        mContext.startActivity(i);
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new AnakAdapter.HistoryViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listAnak.size();
    }


}
