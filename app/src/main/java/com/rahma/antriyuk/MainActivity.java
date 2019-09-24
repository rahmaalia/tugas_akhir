package com.rahma.antriyuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView btRegister=findViewById(R.id.txtDaftar);
        Button btnMoveActivity = findViewById(R.id.btnLogin);
        btnMoveActivity.setOnClickListener(this);
        btRegister.setOnClickListener(this);
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
