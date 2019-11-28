package com.rahma.antriyuk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.antriyuk.apihelper.BaseApiService;
import com.rahma.antriyuk.apihelper.RetrofitClient;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView tanggal;
    BaseApiService mApiInterface;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    Button btnBatal;
    TextView namapoli,namapasien,tvnoantrian;
    ImageView exit;
    int idPoli;
    int idAntri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = this;
        sharedPrefManager = new SharedPrefManager(this);
        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);

        tanggal();
        initComponent();

        final Intent intent = getIntent();
        idPoli = intent.getIntExtra("poliId",1);
        idAntri=intent.getIntExtra("id_antri",1);
        namapasien.setText(getIntent().getExtras().getString("nama"));
        namapoli.setText(getIntent().getExtras().getString("namapoli"));
        tvnoantrian.setText(getIntent().getExtras().getString("noantrian"));




        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface.deleteAntri(idAntri,idPoli).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){

                            AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(mContext);
                            alertdialogBuilder.setTitle("Batalkan Antrian");

                            alertdialogBuilder
                                    .setMessage("Apakah anda yakin?")
                                    .setCancelable(false)
                                    .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Toast.makeText(DetailActivity.this,"Batal Antrian Berhasil", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(DetailActivity.this,BerandaActivity.class);
                                             startActivity(intent);
                                        }
                                    })
                                    .setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = alertdialogBuilder.create();
                            alertDialog.show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this,BerandaActivity.class);
                startActivity(i);
            }
        });
    }

    private void initComponent() {
        btnBatal = findViewById(R.id.btnBatal);
        namapoli = findViewById(R.id.tvnamapoli);
        namapasien = findViewById(R.id.tvnamapasien);
        tvnoantrian = findViewById(R.id.tvnoantrian);
        exit = findViewById(R.id.ivExit);
    }

    public void tanggal (){

        Calendar c = Calendar.getInstance(TimeZone.getDefault());

        String[] namaBulan = {"Januari","Februari","Maret", "April", "Mei", "Juni", "Juli",
                "Agustus", "September", "Oktober", "November",
                "Desember"};

        String [] namaHari = {  "Sabtu", "Minggu", "Senin", "Selasa", "Rabu", "Kamis","Jumat","sabtu"};

        String bulann = namaBulan[c.get(Calendar.MONTH)];
        String harii = namaHari[c.get(Calendar.DAY_OF_WEEK)];

        int tahunn = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);

        tanggal = (TextView) findViewById(R.id.tvtanggal);

        tanggal.setText(" "+harii+","+ " " +date+ " " +bulann+ " " +tahunn);

    }
}
