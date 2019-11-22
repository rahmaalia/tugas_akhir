//package com.rahma.antriyuk;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.rahma.antriyuk.apihelper.BaseApiService;
//import com.rahma.antriyuk.apihelper.RetrofitClient;
//import com.rahma.antriyuk.fragment.ProfilFragment;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class EditActivity extends AppCompatActivity {
//
//    EditText  edtNama, edtNomor;
//    Button btUpdate;
//    String namas,nomers;
//    BaseApiService mApiInterface;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit);
//
//        edtNama = findViewById(R.id.etNama);
//        edtNomor = findViewById(R.id.etNo_telp);
//        btUpdate = findViewById(R.id.btnSimpan);
//
//
//
//        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
//        btUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nama = edtNama.getText().toString();
//                String nomor = edtNomor.getText().toString();
//
//                if (nama.isEmpty()) {
//                    edtNama.setError("Nama Kosong");
//                    return;
//                }
//
//                if (nomor.isEmpty()) {
//                    edtNomor.setError("Nomor Kosong");
//                    return;
//                }
//            }
//        });
//        Intent intent = getIntent();
//        namas = intent.getStringExtra("nama");
//        nomers = intent.getStringExtra("nomer");
//
//        edtNama.setText(namas);
//        edtNomor.setText(nomers);
//    }
//
//    private void Edit(){
//
//        edtNama.setEnabled(false);
//        edtNomor.setEnabled(false);
//
//        btUpdate.setEnabled(false);
//
//        mApiInterface = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
//        mApiInterface.update(namas,nomers).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()){
//                    Toast.makeText(EditActivity.this, "Success to update user info", Toast.LENGTH_SHORT).show();
//                    setResult(Activity.RESULT_OK);
//                    finish();
//                } else {
//                    Toast.makeText(EditActivity.this, "Failed to update user info", Toast.LENGTH_SHORT).show();
//
//                    edtNama.setEnabled(true);
//                    edtNomor.setEnabled(true);
//
//                    btUpdate.setEnabled(true);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//    }
//}
