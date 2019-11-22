package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ambilAntrian extends AppCompatActivity {

    TextView namaPoli,noantrian,tanggal;
    String kodeAntrian,namaPolis,nama,kota_lahir,alamat,jenis_kelamin,tgl_lahir,no_identitas,noAntrianSblm;
    Button btnAntrian;
    Context mContext;
    ImageView btnback;
    int noAntrian,id_poli,polisId;
    BaseApiService mApiInterface;
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_antrian);

        namaPoli = findViewById(R.id.AA_namapoli);
        noantrian = findViewById(R.id.result_antrianSaatIni);
        btnAntrian = findViewById(R.id.btnAmbilantrian);
        btnback = findViewById(R.id.btn_back);

        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;

        Intent intent = getIntent();
        namaPolis = intent.getStringExtra("nPoli");
        polisId = intent.getIntExtra("idpoli",1);

        namaPoli.setText(namaPolis);
        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);



        no_identitas = intent.getStringExtra("no_identitas");
        nama = intent.getStringExtra("nama");
        kota_lahir = intent.getStringExtra("kota_lhr");
        tgl_lahir = intent.getStringExtra("tanggal");
        alamat = intent.getStringExtra("alamat");
        jenis_kelamin = intent.getStringExtra("jnskelamin");

        noAntrian = sharedPrefManager.getSpNoantri();


        if (polisId == 1){
            noAntrianSblm = "PA-" +noAntrian;
        }else if (polisId == 2){
            noAntrianSblm = "PG-" +noAntrian;
        }else if (polisId == 3){
            noAntrianSblm = "PU-" +noAntrian;
        }else if (polisId == 4){
            noAntrianSblm = "PM-" +noAntrian;
        }
        noantrian.setText(noAntrianSblm);

        if (polisId == 1){
            kodeAntrian = "PA-" +(noAntrian+1);
        }else if (polisId == 2){
            kodeAntrian = "PG-" +(noAntrian+1);
        }else if (polisId == 3){
            kodeAntrian = "PU-" +(noAntrian+1);
        }else if (polisId == 4){
            kodeAntrian = "PM-" +(noAntrian+1);
        }

        btnAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface.postDataPasien(polisId,no_identitas,nama,kota_lahir,tgl_lahir,alamat,jenis_kelamin,kodeAntrian)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    try {
                                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                                        if (jsonRESULT.getString("pesan").equals("berhasil")){
                                            Toast.makeText(mContext,"berhasil",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(ambilAntrian.this,StrukActivity.class);
                                            i.putExtra("kode_antri",kodeAntrian);
                                            i.putExtra("nPoli", namaPolis);
                                            startActivity(i);
                                            finish();
                                        }else {
                                            Toast.makeText(mContext,"Gagal",Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ambilAntrian.this,JadwalAnak.class);
                startActivity(i);
            }
        });

    }



}
