package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrigigi;
import com.rahma.antriyuk.R;

import java.util.ArrayList;
import java.util.List;

public class GigiAdapter extends RecyclerView.Adapter<GigiAdapter.GigiViewHolder> {
    private List<MAntrigigi> mAntrigigis;
    Context mContext;

    public GigiAdapter(Context mContext, List<MAntrigigi> antrigigis) {
        this.mContext = mContext;
        mAntrigigis  = antrigigis;
    }

    @NonNull
    @Override
    public GigiAdapter.GigiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new GigiAdapter.GigiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GigiAdapter.GigiViewHolder holder, int position) {
        final MAntrigigi antrigigi  = mAntrigigis.get(position);
        holder.namapoligigi.setText(antrigigi.getNama());
        holder.noantriangigi.setText(antrigigi.getNoAntrian());
    }

    @Override
    public int getItemCount() {
        return mAntrigigis.size();
    }

    public class GigiViewHolder extends RecyclerView.ViewHolder {

        public final TextView namapoligigi,noantriangigi;

        public GigiViewHolder(@NonNull View itemView) {
            super(itemView);
            namapoligigi = itemView.findViewById(R.id.H_namapasien);
            noantriangigi = itemView.findViewById(R.id.H_noantri);

        }
    }
}
