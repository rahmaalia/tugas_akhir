package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.antriyuk.Entity.EHistory;
import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.R;

import java.util.ArrayList;
import java.util.List;

public class AnakAdapter extends RecyclerView.Adapter<AnakAdapter.HistoryViewHolder> {
    private List<MAntrianak> listAnak;
    Context mContext;


    public AnakAdapter(Context context, List<MAntrianak> data_history) {
        this.mContext = context;
        listAnak  = data_history;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        public final TextView namapasien,noantrian;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            namapasien = itemView.findViewById(R.id.H_namapasien);
            noantrian = itemView.findViewById(R.id.H_noantri);
        }
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new AnakAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        final MAntrianak antrianak  = listAnak.get(position);
        holder.namapasien.setText(antrianak.getNama());
        holder.noantrian.setText(antrianak.getNoAntrian());
    }

    @Override
    public int getItemCount() {
        return listAnak.size();
    }


}
