package com.rahma.antriyuk.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rahma.antriyuk.Adapter.AnakAdapter;
import com.rahma.antriyuk.Adapter.GigiAdapter;
import com.rahma.antriyuk.Adapter.MataAdapter;
import com.rahma.antriyuk.Adapter.UmumAdapter;
import com.rahma.antriyuk.Entity.MAntrianak;
import com.rahma.antriyuk.Entity.MAntrigigi;
import com.rahma.antriyuk.Entity.MAntrimata;
import com.rahma.antriyuk.Entity.MAntriumum;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.model.Mantri;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class BerlangsungFragment extends Fragment {
    String status;
    int id_user;
    Context mContext;
    BaseApiService mApiService;
    private ArrayList<MAntrianak> listAnak = new ArrayList<>();
    private ArrayList<MAntrigigi> listGigi = new ArrayList<>();
    private ArrayList<MAntriumum> listUmum = new ArrayList<>();
    private ArrayList<MAntrimata> listmata = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    AnakAdapter anakAdapter;
    GigiAdapter gigiAdapter;
    UmumAdapter umumAdapter;
    MataAdapter mataAdapter;

    RecyclerView rvAnak,rvGigi,rvUmum,rvMata;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berlangsung, container, false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        mContext = getContext();
        sharedPrefManager=new SharedPrefManager(getContext());
        id_user=sharedPrefManager.getSpIduser();
        status="berlangsung";
        rvAnak = view.findViewById(R.id.Hrc);
        rvGigi = view.findViewById(R.id.Hrc2);
        rvUmum = view.findViewById(R.id.Hrc3);
        rvMata = view.findViewById(R.id.Hrc4);
        getHistoryAnak();
        mApiService.HistoryRequest(id_user,status).enqueue(new Callback<Mantri>() {
            @Override
            public void onResponse(Call<Mantri> call, Response<Mantri> response) {
                if (response.isSuccessful()){
                    final List<MAntrianak> mAntrianaks = response.body().getAntriAnak();
                    final List<MAntrigigi> mAntrigigis = response.body().getAntriGigi();
                    final List<MAntriumum> mAntriumums = response.body().getAntriUmum();
                    final List<MAntrimata> mAntrimatas = response.body().getAntriMata();

                    rvAnak.setAdapter(new AnakAdapter(mContext,mAntrianaks));
                    anakAdapter.notifyDataSetChanged();
                    rvGigi.setAdapter(new GigiAdapter(mContext,mAntrigigis));
                    gigiAdapter.notifyDataSetChanged();
                    rvUmum.setAdapter(new UmumAdapter(mContext,mAntriumums));
                    umumAdapter.notifyDataSetChanged();
                    rvMata.setAdapter(new MataAdapter(mContext,mAntrimatas));
                    mataAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Mantri> call, Throwable t) {

            }
        });

        return view;
    }

    private void getHistoryAnak() {
        anakAdapter = new AnakAdapter(mContext, listAnak);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        rvAnak.setLayoutManager(mLayoutManager);
        rvAnak.setItemAnimator(new DefaultItemAnimator());

        gigiAdapter = new GigiAdapter(mContext, listGigi);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        rvGigi.setLayoutManager(mLayoutManager2);
        rvGigi.setItemAnimator(new DefaultItemAnimator());

        umumAdapter = new UmumAdapter(mContext, listUmum);
        RecyclerView.LayoutManager mLayoutManager3 = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        rvUmum.setLayoutManager(mLayoutManager3);
        rvUmum.setItemAnimator(new DefaultItemAnimator());

        mataAdapter = new MataAdapter(mContext, listmata);
        RecyclerView.LayoutManager mLayoutManager4 = new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false);
        rvMata.setLayoutManager(mLayoutManager4);
        rvMata.setItemAnimator(new DefaultItemAnimator());
    }

}