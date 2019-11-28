package com.rahma.antriyuk.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahma.antriyuk.MainActivity;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.w3c.dom.Text;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ProfilFragment extends Fragment {

    TextView TvResultNama,resultUsername,resultNotelp;
    ImageView edit;
    public static ProfilFragment pf;
    String nama,nomer,username;
    SharedPrefManager sharedPrefManager;
    View view;
    Button bt_keluar;
    Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        super.onCreate(savedInstanceState);

        mContext = getContext();

        sharedPrefManager = new SharedPrefManager(mContext);
        initComponenrt();
        TvResultNama.setText(sharedPrefManager.getSpUsername());
        resultUsername.setText(sharedPrefManager.getSpNama());
        resultNotelp.setText(sharedPrefManager.getSpTelp());

        nama = sharedPrefManager.getSpNama();
        nomer = sharedPrefManager.getSpTelp();
        username = sharedPrefManager.getSpUsername();
        mContext = getContext();
        pf = this;


        bt_keluar = (Button) view.findViewById(R.id.bt_keluar);
        bt_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(mContext);
                alertdialogBuilder.setTitle("Keluar");

                alertdialogBuilder
                        .setMessage("Apakah anda yakin?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPrefManager = new SharedPrefManager(getContext());
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, false);
                                startActivity(new Intent(getContext(), MainActivity.class)
                                 .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
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
        });

//        edit = view.findViewById(R.id.btnEdit);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getContext(), EditActivity.class);
//                i.putExtra("nama",nama);
//                i.putExtra("nomer",nomer);
//                i.putExtra("username",username);
//                startActivityForResult(i, 0x01);
//            }
//        });
//
        return view;
    }


    private void initComponenrt() {
        TvResultNama = view.findViewById(R.id.NamaUtama);
        resultUsername = view.findViewById(R.id.p_user);
        resultNotelp = view.findViewById(R.id.p_notelp);

    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 0x01 && resultCode == RESULT_OK) {
//            llInfo.setVisibility(View.GONE);
//            loadUserInfo();
//        }
//    }
}
