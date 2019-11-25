package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.R;

import java.util.ArrayList;
import java.util.List;

public class MataAdapter extends RecyclerView.Adapter<MataAdapter.MataViewHolder> {
    private List<MAntrimata> antrimatas;
    Context mContext;

    public MataAdapter(Context mContext, List<MAntrimata> mAntrimatas) {
        this.mContext = mContext;
        antrimatas  = mAntrimatas;
    }

    @NonNull
    @Override
    public MataAdapter.MataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new MataAdapter.MataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MataAdapter.MataViewHolder holder, int position) {
        final MAntrimata antrimata = antrimatas.get(position);
        holder.namapasienmata.setText(antrimata.getNama());
        holder.noantrianmata.setText(antrimata.getNoAntrian());

    }

    @Override
    public int getItemCount() {
        return antrimatas.size();
    }

    public class MataViewHolder extends RecyclerView.ViewHolder {
        public final TextView namapasienmata,noantrianmata;
        public MataViewHolder(@NonNull View itemView) {
            super(itemView);
            namapasienmata = itemView.findViewById(R.id.H_namapasien);
            noantrianmata = itemView.findViewById(R.id.H_noantri);


        }
    }
}
