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
import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.R;

import java.util.ArrayList;
import java.util.List;

public class MataAdapter extends RecyclerView.Adapter<MataAdapter.MataViewHolder> {
    private List<MAntrimata> antrimatas;
    Context mContext;
    CardView cardView;

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
            cardView = itemView.findViewById(R.id.cvHistory);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Intent i = new Intent(mContext, DetailActivity.class);
                        i.putExtra("id_antri",antrimatas.get(position).getId());

                        i.putExtra("nama", antrimatas.get(position).getNama());
                        i.putExtra("noantrian", antrimatas.get(position).getNoAntrian());
                        i.putExtra("namapoli", antrimatas.get(position).getNamaPoli());
                        mContext.startActivity(i);
                    }
                }
            });
        }
    }
}
