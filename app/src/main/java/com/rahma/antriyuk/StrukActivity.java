package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahma.antriyuk.fragment.HistoryFragment;
import com.rahma.antriyuk.fragment.HomeFragment;

import java.util.Calendar;
import java.util.TimeZone;

public class StrukActivity extends AppCompatActivity {
    TextView tanggal,noantri,namapoli;
    String noantrians,namapolis,namapasien;
    int idpoli,id_antri;
    ImageView exit;
    Button btndetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);

        noantri = findViewById(R.id.noantrians);
        namapoli = findViewById(R.id.poliname);
        exit = findViewById(R.id.exit);
        btndetail = findViewById(R.id.btn_detail);

        final Intent intent = getIntent();
        noantrians = intent.getStringExtra("kode_antri");
        namapolis = intent.getStringExtra("nPoli");
        idpoli = intent.getIntExtra("idpoli",1);
        namapasien = intent.getStringExtra("nama");
        id_antri = intent.getIntExtra("id_antrian",1);
        noantri.setText(noantrians);
        namapoli.setText(namapolis);

        tanggal();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StrukActivity.this, BerandaActivity.class);
                startActivity(i);
            }
        });

        btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StrukActivity.this,DetailActivity.class);
                i.putExtra("poliId",idpoli);
                i.putExtra("namapoli",namapolis);
                i.putExtra("noantrian",noantrians);
                i.putExtra("nama",namapasien);
                i.putExtra("id_antri",id_antri);
                startActivity(i);
                finish();
            }
        });

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

        tanggal = (TextView) findViewById(R.id.tanggals);

        tanggal.setText(" "+harii+","+ " " +date+ " " +bulann+ " " +tahunn);

    }


}
