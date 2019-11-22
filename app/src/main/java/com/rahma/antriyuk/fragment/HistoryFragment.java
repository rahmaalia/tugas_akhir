package com.rahma.antriyuk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rahma.antriyuk.Adapter.HistoryAdapter;
import com.rahma.antriyuk.Entity.EHistory;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.model.Mhistory;
import com.rahma.antriyuk.ui.main.BerlangsungFragment;
import com.rahma.antriyuk.ui.main.SectionsPagerAdapter;
import com.rahma.antriyuk.ui.main.SelesaiFragment;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    RecyclerView mRecyclerView;
    View view;
    Context mContext;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        mContext = getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.Hrc);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(mContext,getFragmentManager());
        sectionsPagerAdapter.addFragment(new BerlangsungFragment(),"Berlangsung");
        sectionsPagerAdapter.addFragment(new SelesaiFragment(),"Selesai");
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        history();
        return view;
    }

    private void history() {
//        BaseApiService mApiService = RetrofitClient.getAPIService();
//
//        Call<Mhistory> historyCall = mApiService.getHistory();
//        historyCall.enqueue(new Callback<Mhistory>() {
//            @Override
//            public void onResponse(Call<Mhistory> call, Response<Mhistory> response) {
//                if (response.isSuccessful()){
//                    List<EHistory> data_history =
//                            response.body().getBio();
//                    boolean status =
//                            response.body().getStatus();
//
//                    if (status == true){
//                        HistoryAdapter adapter = new HistoryAdapter(getContext(),data_history);
//                        mRecyclerView.setAdapter(adapter);
//                    } else {
//                        Toast.makeText(getContext(),"Tidak ada History",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Mhistory> call, Throwable t) {
//
//            }
//        });
   }
}
