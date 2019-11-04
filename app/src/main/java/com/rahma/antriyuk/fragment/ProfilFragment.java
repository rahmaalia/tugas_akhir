package com.rahma.antriyuk.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahma.antriyuk.MainActivity;
import com.rahma.antriyuk.R;
import com.rahma.antriyuk.sharedpref.SharedPrefManager;

import org.w3c.dom.Text;

import static android.content.Context.MODE_PRIVATE;

public class ProfilFragment extends Fragment {

    TextView TvResultNama,resultUsername;


    SharedPrefManager sharedPrefManager;
    View view;
    Button bt_keluar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profil, container, false);
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(getContext());
        initComponenrt();
        TvResultNama.setText(sharedPrefManager.getSpNama());
        resultUsername.setText(sharedPrefManager.getSpNama());


        bt_keluar = (Button) view.findViewById(R.id.bt_keluar);
        bt_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager = new SharedPrefManager(getContext());
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, false);
                startActivity(new Intent(getContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        return view;
    }

    private void initComponenrt() {
        TvResultNama = view.findViewById(R.id.NamaUtama);
        resultUsername = view.findViewById(R.id.p_user);
    }
}
