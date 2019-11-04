package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {

        EditText etNama,etUsername,etNoTelp,etPassword;
        Button btnRegister;
        TextView btnMasuk;
        ProgressDialog loading;
        String cPassword,Password;
        Context mContext;
        BaseApiService mApiService;
        TextInputLayout layoutnama , layoutUsername, layoutNotelp, layoutPassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_daftar);

            mContext = this;
            mApiService = RetrofitClient.getAPIService();
            initComponens();
        }

        private void initComponens() {

            etNama = findViewById(R.id.nama);
            etUsername =  findViewById(R.id.d_username);
            etNoTelp = findViewById(R.id.noTelp);
            etPassword = findViewById(R.id.katasandi);

            btnRegister =  findViewById(R.id.btnDaftar);
            btnMasuk =  findViewById(R.id.tvMasuk);

            Password=etPassword.getText().toString();

            layoutnama = findViewById(R.id.layoutName);
            layoutUsername = findViewById(R.id.layoutUsername);
            layoutNotelp = findViewById(R.id.layoutNotelp);
            layoutPassword = findViewById(R.id.layoutKatasandi);


            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (etNama.length()==0 || etUsername.length()==0 || etNoTelp.length()==0 || etPassword.length()==0) {
                        Toast.makeText(mContext, "Isi Semua Field", Toast.LENGTH_LONG).show();
                        if (etNama.length()==0) {
                            layoutnama.setError("Nama tidak boleh kosong");
                        }
                        else if(etNama.length()!=0){
                            layoutnama.setError(null);
                            layoutnama.setErrorEnabled(false);
                        }

                        if (etUsername.length()==0) {
                            layoutUsername.setError("Username tidak boleh kosong");
                        }
                        else {
                            layoutUsername.setError(null);
                            layoutUsername.setErrorEnabled(false);
                        }

                        if (etNoTelp.length()==0) {
                            layoutNotelp.setError("NoTelp tidak boleh kosong");
                        }
                        else{
                            layoutNotelp.setError(null);
                            layoutNotelp.setErrorEnabled(false);
                        }

                        if (etPassword.length()==0) {
                            layoutPassword.setError("Password tidak boleh kosong");
                        }
                        else{
                            layoutPassword.setError(null);
                            layoutPassword.setErrorEnabled(false);
                        }


                    } else {

                        requestRegister();
                    }
                }
                private void requestRegister() {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true,false);
                    mApiService.registerRequest(etNama.getText().toString(),
                            etUsername.getText().toString(),
                            etNoTelp.getText().toString(),
                            etPassword.getText().toString()
                            )
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Log.i("debug","onResponse: BERHASIL");
                                        loading.dismiss();
                                        try {
                                            JSONObject jsonRESULT = new JSONObject(response.body().string());
                                            if (jsonRESULT.getString("status").equals("true")) {
                                                Toast.makeText(mContext, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(DaftarActivity.this, MainActivity.class);
                                                startActivity(intent);
                                            }
                                            else {
                                                Toast.makeText(mContext, "Akun Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                        }catch (IOException e){
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Log.i("debug", "onResponse : GA BERHASIL");
                                        loading.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                    Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            });

            btnMasuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(mContext, MainActivity.class));
                }
            });
        }
}
