package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.fragment.HomeFragment;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etpassword;
    Button btnLogin;
    TextView btnRegister;
    ProgressDialog loading;
    ConstraintLayout constraintLayout;
    Context mContext;
    BaseApiService mApiService;
    TextInputLayout layoutu,layoutp;

    SharedPrefManager sharedPrefManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout=findViewById(R.id.conslayout);
        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSpLogin()){
            startActivity(new Intent(MainActivity.this, BerandaActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }


//        etUsername = findViewById(R.id.username);
//        etpassword = findViewById(R.id.password);
        TextView btRegister=findViewById(R.id.txtDaftar);
        Button btnMoveActivity = findViewById(R.id.btnLogin);
        btnMoveActivity.setOnClickListener(this);
        btRegister.setOnClickListener(this);
        
        mContext =MainActivity.this;
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        initComponents();
    }

    private void initComponents() {
        layoutp=findViewById(R.id.layoutpassword);
        layoutu=findViewById(R.id.layoutuser);
        etUsername = (EditText) findViewById(R.id.username);
        etpassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (TextView) findViewById(R.id.txtDaftar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUsername.length()==0){
                    layoutu.setError("Username Harus Diisi");

                }
                else if (etUsername.length()!=0){
                    layoutu.setError(null);
                    layoutu.setErrorEnabled(false);
                }
                if(etpassword.length()==0){
                    layoutp.setError("Password Harus Diisi");

                }
                else if (etpassword.length()!=0){
                    layoutp.setError(null);
                    layoutp.setErrorEnabled(false);
                }
                if(etUsername.length()!=0 && etpassword.length()!=0){
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true,false);
                mApiService.loginRequest(etUsername.getText().toString(), etpassword.getText().toString())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    loading.dismiss();
                                    try {
                                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                                        if (jsonRESULT.getString("status").equals("true")){

                                            Toast.makeText(mContext, "Berhasil login", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, BerandaActivity.class);
                                            String nama = jsonRESULT.getJSONObject("data").getString("nama");
                                            String notelp = jsonRESULT.getJSONObject("data").getString("no_telp");
                                            String username = jsonRESULT.getJSONObject("data").getString("username");
                                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                            sharedPrefManager.saveSPString(SharedPrefManager.SP_TELP, notelp);
                                            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
                                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, true);
                                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                            finish();
                                        }
                                        else{
                                            String error = jsonRESULT.getString("error");
                                            Toast.makeText(mContext,error, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    } catch (IOException e){
                                        e.printStackTrace();
                                    }
                                } else {
                                    loading.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e("debug", "onFailure : ERROR > " + t.toString());
                                loading.dismiss();
                            }
                        });}
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, DaftarActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                Intent moveIntent = new Intent(MainActivity.this, BerandaActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.txtDaftar:
                Intent intent=new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(intent);
                break;
        }
    }
}
