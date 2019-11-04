package com.rahma.antriyuk.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rahma.antriyuk.Adapter.Adapter;
import com.rahma.antriyuk.Entity.EPoli;
import com.rahma.antriyuk.JadwalAnak;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.model.MPoli;
import com.rahma.antriyuk.modelVp;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    TextView tvResultNama,tvNamaPoli,tvJamBuka,tvJamTutup;
    String resultNama;

    ViewPager viewPager;
    Adapter adapter;
    List<modelVp> model;
    List<EPoli> mPOli;
    BaseApiService mApiService;
    private TextView[] dots;
    TabLayout linearLayout;
    String[] nama_poli = null;
    String[] nama_dokter = null;
    String[] jamm = null;
    TextView namapoli,namadokter,time;
    Button bt_pilih;
    View view;
    SharedPrefManager sharedPrefManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(getContext());

        initComponents();
        tvResultNama.setText(sharedPrefManager.getSpNama());
        model = new ArrayList<>();
        model.add(new modelVp(R.drawable.anak));
        model.add(new modelVp(R.drawable.gigi));
        model.add(new modelVp(R.drawable.klinik));
        model.add(new modelVp(R.drawable.mata));
        adapter = new Adapter(model, getContext());
        viewPager.setAdapter(adapter);
        viewPager.setPadding(30, 0, 30, 0);
        viewPager.addOnPageChangeListener(viewListener);

        String[] namaPoli = {
                "Poli Anak",
                "Poli Gigi",
                "Poli Umum",
                "Poli Mata",
        };

        String[] namaDokter = {
                "dr.Rahma Aliaputri",
                "dr.Artha putri",
                "dr.Fadhilah januar",
                "dr.nur Widiastuty"
        };

        String[] jam = {
                "08.00 - 13.00",
                "08.00 - 12.00",
                "08.00 - 11.30",
                "07.30 - 10.30"
        };


        linearLayout.setupWithViewPager(viewPager, true);

        nama_poli = namaPoli;
        nama_dokter = namaDokter;
        jamm = jam;

        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        initComponen();

        return view;
    }

    private void initComponen() {
        tvNamaPoli = view.findViewById(R.id.tv_poli);
        tvJamBuka = view.findViewById(R.id.tv_time);
        tvJamTutup = view.findViewById(R.id.tv_timeT);

        mApiService.getPoli().enqueue(new Callback<MPoli>() {
            @Override
            public void onResponse(Call<MPoli> call, Response<MPoli> response) {
                if (response.isSuccessful()){
                    final List<EPoli> epoli = response.body().getBio();
                }
            }

            @Override
            public void onFailure(Call<MPoli> call, Throwable t) {

            }
        });
    }

    private void initComponents() {
        tvResultNama = view.findViewById(R.id.tvNamaResult);
        linearLayout = view.findViewById(R.id.dot);
        viewPager = view.findViewById(R.id.vp);
        bt_pilih=view.findViewById(R.id.btn_pilih);
        namapoli = view.findViewById(R.id.tv_poli);
        namadokter = view.findViewById(R.id.tv_doctor);
        time = view.findViewById(R.id.tv_time);
    }

    private Intent getIntent() {
        return null;
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
            final EPoli epoli = mPOli.get(position);

            if (position < (adapter.getCount())&& position < (nama_poli.length )){
                namapoli.setText(
                       epoli.getNamaPoli()
                );
                namadokter.setText(
                        (nama_dokter[position])
                );
                time.setText(
                        (jamm[position])
                );

                bt_pilih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(position==0){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            startActivity(intent);

                        }
                        if(position==1){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            startActivity(intent);
                        }
                        if(position==2){
                            Intent intent=new Intent(getActivity(), JadwalAnak.class);
                            startActivity(intent);
                        }
                        if(position==3){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
