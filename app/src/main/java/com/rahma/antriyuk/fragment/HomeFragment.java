package com.rahma.antriyuk.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rahma.antriyuk.Adapter.Adapter;
import com.rahma.antriyuk.Entity.EPoli;
import com.rahma.antriyuk.JadwalAnak;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.modelVp;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView tvResultNama,tvNamaPoli,tvJamBuka,tvJamTutup;
    String namapoli_a,namadokter_a,jambuka_a,jamtutup_a,namapoli_g,namadokter_g,jambuka_g,jamtutup_g,
            namapoli_u, namadokter_u, jambuka_u, jamtutup_u,  namapoli_m, namadokter_m, jambuka_m, jamtutup_m;
    int idpoli_a,idpoli_g,idpoli_u,idpoli_m;
    ViewPager viewPager;
    Adapter adapter;
    List<modelVp> model;
    List<EPoli> mPOli;
    BaseApiService mApiService;
    Context mContext;
    TabLayout linearLayout;
    String[] namaPolis ,namaDokters ,jambukaas , jamtutupps ;
    int[] idP = null;
    TextView namapoli,namadokter,time,timeT;
    Button bt_pilih;
    View view;
    SharedPrefManager sharedPrefManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(getContext());
        tvNamaPoli = view.findViewById(R.id.tv_poli);
        tvJamBuka = view.findViewById(R.id.tv_time);
        tvJamTutup = view.findViewById(R.id.tv_timeT);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        mApiService.getPoli().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                        if (jsonRESULT.getString("status").equals("true")) {
                            //polianak
                            namapoli_a = jsonRESULT.getJSONObject("polianak").getString("nama_poli");
                            namadokter_a = jsonRESULT.getJSONObject("polianak").getString("nama_dokter");
                            jambuka_a = jsonRESULT.getJSONObject("polianak").getString("jam_buka");
                            jamtutup_a = jsonRESULT.getJSONObject("polianak").getString("jam_tutup");
                            idpoli_a = jsonRESULT.getJSONObject("polianak").getInt("id");
                            //poligigi

                            namapoli_g = jsonRESULT.getJSONObject("poligigi").getString("nama_poli");
                            namadokter_g = jsonRESULT.getJSONObject("poligigi").getString("nama_dokter");
                            jambuka_g = jsonRESULT.getJSONObject("poligigi").getString("jam_buka");
                            jamtutup_g = jsonRESULT.getJSONObject("poligigi").getString("jam_tutup");
                            idpoli_g = jsonRESULT.getJSONObject("poligigi").getInt("id");
                            //poliumum
                            namapoli_u = jsonRESULT.getJSONObject("poliumum").getString("nama_poli");
                            namadokter_u = jsonRESULT.getJSONObject("poliumum").getString("nama_dokter");
                            jambuka_u = jsonRESULT.getJSONObject("poliumum").getString("jam_buka");
                            jamtutup_u = jsonRESULT.getJSONObject("poliumum").getString("jam_tutup");
                            idpoli_u = jsonRESULT.getJSONObject("poliumum").getInt("id");
                            //polimata
                            namapoli_m = jsonRESULT.getJSONObject("polimata").getString("nama_poli");
                            namadokter_m = jsonRESULT.getJSONObject("polimata").getString("nama_dokter");
                            jambuka_m = jsonRESULT.getJSONObject("polimata").getString("jam_buka");
                            jamtutup_m = jsonRESULT.getJSONObject("polimata").getString("jam_tutup");
                            idpoli_m = jsonRESULT.getJSONObject("polimata").getInt("id");


                        } else {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                        }}
                    catch(JSONException e){
                        e.printStackTrace();
                    } catch(IOException e){
                        e.printStackTrace();
                    }}}

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

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

        linearLayout.setupWithViewPager(viewPager, true);

        return view;
    }

    private void initComponents() {
        tvResultNama = view.findViewById(R.id.tvNamaResult);
        linearLayout = view.findViewById(R.id.dot);
        viewPager = view.findViewById(R.id.vp);
        bt_pilih=view.findViewById(R.id.btn_pilih);
        namapoli = view.findViewById(R.id.tv_poli);
        namadokter = view.findViewById(R.id.tv_doctor);
        time = view.findViewById(R.id.tv_time);
        timeT =view.findViewById(R.id.tv_timeT);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
            final String[] namaPoli = {
                    namapoli_a,
                    namapoli_g,
                    namapoli_u,
                    namapoli_m,
            };

            String[] namaDokter = {
                    namadokter_a,
                    namadokter_g,
                    namadokter_u,
                    namadokter_m
            };

            final String[] jambukaa = {
                    jambuka_a,
                    jambuka_g,
                    jambuka_u,
                    jambuka_m
            };

            final String[] jamtutupp = {
                    jamtutup_a,
                    jamtutup_g,
                    jamtutup_u,
                    jamtutup_m
            };
            final int[] id = {
                    idpoli_a,
                    idpoli_g,
                    idpoli_u,
                    idpoli_m
            };
            if (position < (adapter.getCount())&& position <(namaPoli.length )){
                namapoli.setText(
                        (namaPoli[position])
                );
                namadokter.setText(
                        (namaDokter[position])
                );
                time.setText(
                        (jambukaa[position])
                );
                timeT.setText(
                        (jamtutupp[position])
                );

                bt_pilih.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(position==0){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            intent.putExtra("idPoli",id[0]);
                            intent.putExtra("namaPoli",namaPoli[0]);
                            intent.putExtra("jamBuka",jambukaa[0]);
                            intent.putExtra("jamTutup",jamtutupp[0]);

                            startActivity(intent);

                        }
                        if(position==1){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            intent.putExtra("idPoli",id[1]);
                            intent.putExtra("namaPoli",namaPoli[1]);
                            intent.putExtra("jamBuka",jambukaa[1]);
                            intent.putExtra("jamTutup",jamtutupp[1]);

                            startActivity(intent);
                        }
                        if(position==2){
                            Intent intent=new Intent(getActivity(), JadwalAnak.class);
                            intent.putExtra("idPoli",id[2]);
                            intent.putExtra("namaPoli",namaPoli[2]);
                            intent.putExtra("jamBuka",jambukaa[2]);
                            intent.putExtra("jamTutup",jamtutupp[2]);
                            startActivity(intent);
                        }
                        if(position==3){
                            Intent intent=new Intent(getActivity(),JadwalAnak.class);
                            intent.putExtra("idPoli",id[3]);
                            intent.putExtra("namaPoli",namaPoli[3]);
                            intent.putExtra("jamBuka",jambukaa[3]);
                            intent.putExtra("jamTutup",jamtutupp[3]);
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





