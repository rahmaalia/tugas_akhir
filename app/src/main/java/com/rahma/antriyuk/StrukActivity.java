package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class StrukActivity extends AppCompatActivity {
    TextView tanggal,noantri,namapoli;
    String noantrians,namapolis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);

        noantri = findViewById(R.id.noantrians);
        namapoli = findViewById(R.id.poliname);

        Intent intent = getIntent();
        noantrians = intent.getStringExtra("kode_antri");
        namapolis = intent.getStringExtra("nPoli");
        noantri.setText(noantrians);
        namapoli.setText(namapolis);

        tanggal();
    }
    public void tanggal (){

        Calendar c = Calendar.getInstance(TimeZone.getDefault());

        String[] namaBulan = {"Januari","Februari","Maret", "April", "Mei", "Juni", "Juli",
                "Agustus", "September", "Oktober", "November",
                "Desember"};

        String [] namaHari = {  "Sabtu", "Minggu", "Senin", "Selasa", "Rabu", "Kamis","Jumat"};

        String bulann = namaBulan[c.get(Calendar.MONTH)];
        String harii = namaHari[c.get(Calendar.DAY_OF_WEEK)];

        int tahunn = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);

        tanggal = (TextView) findViewById(R.id.tanggals);

        tanggal.setText(" "+harii+","+ " " +date+ " " +bulann+ " " +tahunn);

    }
}
