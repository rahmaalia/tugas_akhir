package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;

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
    String kodeAntrian,polisId,namaPolis,namapoli,Noantrian;
    Button btnAntrian;
    Context mContext;
    int noAntrian,idPoli;
    BaseApiService mApiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_antrian);

        namaPoli = findViewById(R.id.AA_namapoli);
        noantrian = findViewById(R.id.result_antrianSaatIni);
        btnAntrian = findViewById(R.id.btnAmbilantrian);

        mContext = this;

        Intent intent = getIntent();
        namaPolis = intent.getStringExtra("nPoli");
        polisId = intent.getStringExtra("polisId");
        noAntrian = intent.getIntExtra("noAntrian",1);
        String noAntrianSblm = String.valueOf(noAntrian -1);
        namaPoli.setText(namaPolis);
        noantrian.setText(noAntrianSblm);
        idPoli = Integer.parseInt(polisId);

        if (polisId.equals("1")){
            kodeAntrian = "PA" +noAntrian;
        }else if (polisId.equals("2")){
            kodeAntrian = "PG" +noAntrian;
        }else if (polisId.equals("3")){
            kodeAntrian = "PU" +noAntrian;
        }else if (polisId.equals("4")){
            kodeAntrian = "PM" +noAntrian;
        }

        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        btnAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface.postAntri(kodeAntrian,noAntrian,idPoli)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                try {
                                    JSONObject jsonRESULT = new JSONObject(response.body().string());
                                    if (jsonRESULT.getString("pesan").equals("berhasil")){
                                        namapoli = jsonRESULT.getJSONObject("bio").getString("nama_poli");
                                        Noantrian = jsonRESULT.getJSONObject("bio").getString("no_antrian");
                                        Toast.makeText(mContext,"berhasil",Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(ambilAntrian.this,StrukActivity.class);
                                        i.putExtra("namapoli",namapoli);
                                        i.putExtra("noantrian",Noantrian);
                                        startActivity(i);

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

    }



}
