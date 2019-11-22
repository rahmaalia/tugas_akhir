package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.antriyuk.Entity.EHistory;
import com.rahma.antriyuk.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    public HistoryAdapter(LayoutInflater mInflanter) {

    }

    public HistoryAdapter(Context context, List<EHistory> data_history) {
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        public final TextView namapoli,noantrian;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            namapoli = itemView.findViewById(R.id.H_namapoli);
            noantrian = itemView.findViewById(R.id.H_noantri);
        }
    }



    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
