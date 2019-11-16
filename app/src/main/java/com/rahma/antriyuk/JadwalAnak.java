package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
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

public class JadwalAnak extends AppCompatActivity {

    TextView tanggall,bulan,tahun,hari,date,TanggalLahir,namaP,jamB,jamT;
    EditText edtNoIdentitas,edtNama,edtKotaLahir,edtAlamat;
    RadioGroup RjnsKelamin;
    RadioButton RBjnsKelamin;
    Button btnNext;
    Context mContext;
    String tanggal,jeniskelamin,nPoli;
    int id_poli;
    BaseApiService mApiInterface;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "JadwalAnak";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_anak);
        tanggall = findViewById(R.id.tanggal);
        tahun = findViewById(R.id.tahun);
        namaP = findViewById(R.id.namaPoliJdwl);
        jamB = findViewById(R.id.date_bukaPoli);
        jamT = findViewById(R.id.date_tutupPoli);

        mContext = this;

        Intent intent = getIntent();
        nPoli = intent.getStringExtra("namaPoli");
        String jBuka = intent.getStringExtra("jamBuka");
        String jTutup = intent.getStringExtra("jamTutup");
        id_poli=intent.getIntExtra("idPoli",1);
        namaP.setText(nPoli);
        jamB.setText(jBuka);
        jamT.setText(jTutup);

        initComponent();
        tanggal();
        datePicker();
    }

    private void initComponent() {
        edtNoIdentitas = findViewById(R.id.edtNoIdentitasjdwl);
        edtNama = findViewById(R.id.edtNamaJdwl);
        edtKotaLahir = findViewById(R.id.edtKotaJdwl);
        edtAlamat = findViewById(R.id.edtAlamatJdwl);
        TanggalLahir = findViewById(R.id.edtPilihTtlJdwl);
        RjnsKelamin = findViewById(R.id.RbJnsKelamin);
        btnNext = findViewById(R.id.btnNextJdwl);
        int selected = RjnsKelamin.getCheckedRadioButtonId();
        RBjnsKelamin = findViewById(selected);

         jeniskelamin = RBjnsKelamin.getText().toString();

        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiInterface.postDataPasien(id_poli,Integer.parseInt(edtNoIdentitas.getText().toString()), edtNama.getText().toString(), edtKotaLahir.getText().toString(), tanggal, edtAlamat.getText().toString(), jeniskelamin)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    try {
                                        JSONObject jsonRESULT = new JSONObject(response.body().string());
                                        if (jsonRESULT.getString("status").equals("true")){
                                            int noAntrian =jsonRESULT.getJSONObject("bio").getInt("id");
                                            String polisId = jsonRESULT.getJSONObject("bio").getString("polis_id");
                                            Toast.makeText(mContext,"berhasil",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(JadwalAnak.this,ambilAntrian.class);
                                            i.putExtra("noAntrian",noAntrian);
                                            i.putExtra("polisId",polisId);
                                            i.putExtra("nPoli",nPoli);
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

    }

    private void datePicker() {
        date = findViewById(R.id.edtPilihTtlJdwl);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        JadwalAnak.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + day + "/" + month + "/" + year);

                tanggal = day + "/" + month + "/" + year;
                date.setText(tanggal);

            }
        };
    }

    public void tanggal (){

        Calendar c = Calendar.getInstance(TimeZone.getDefault());

        String[] namaBulan = {"Januari","Februari","Maret", "April", "Mei", "Juni", "Juli",
                "Agustus", "September", "Oktober", "Novevenber",
                "Desember"};

        String [] namaHari = {  "Sabtu", "Minggu", "Senin", "Selasa", "Rabu", "Kamis","Jumat"};

        String bulann = namaBulan[c.get(Calendar.MONTH)];
        String harii = namaHari[c.get(Calendar.DAY_OF_WEEK)];

        int tahunn = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);

        bulan = (TextView) findViewById(R.id.bulan);
        hari = (TextView) findViewById(R.id.hari);

        bulan.setText(" "+bulann );
        tahun.setText(" "+tahunn );
        hari.setText(" "+ harii + "," );
        tanggall.setText(" " +date);



    }
}
