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
import com.rahma.antriyuk.Entity.MAntriumum;
import com.rahma.antriyuk.R;

import java.util.List;

public class UmumAdapter extends RecyclerView.Adapter<UmumAdapter.UmumViewHolder> {
    private List<MAntriumum> antriumums;
    Context mContext;
    CardView cardView;

    public UmumAdapter(Context mContext, List<MAntriumum> mAntriumums) {
        this.mContext = mContext;
        antriumums  = mAntriumums;
    }

    @NonNull
    @Override
    public UmumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new UmumAdapter.UmumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UmumAdapter.UmumViewHolder holder, int position) {
        final MAntriumum antriumum = antriumums.get(position);
        holder.namapasienumum.setText(antriumum.getNama());
        holder.noantrianumum.setText(antriumum.getNoAntrian());
    }

    @Override
    public int getItemCount() {
        return antriumums.size();
    }

    public class UmumViewHolder extends RecyclerView.ViewHolder {
        public final TextView namapasienumum,noantrianumum;

        public UmumViewHolder(@NonNull View itemView) {
            super(itemView);
            namapasienumum = itemView.findViewById(R.id.H_namapasien);
            noantrianumum = itemView.findViewById(R.id.H_noantri);
            cardView = itemView.findViewById(R.id.cvHistory);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Intent i = new Intent(mContext, DetailActivity.class);
                        i.putExtra("id_antri",antriumums.get(position).getId());

                        i.putExtra("nama", antriumums.get(position).getNama());
                        i.putExtra("noantrian", antriumums.get(position).getNoAntrian());
                        i.putExtra("namapoli", antriumums.get(position).getNamaPoli());
                        mContext.startActivity(i);
                    }
                }
            });
        }
    }
}
