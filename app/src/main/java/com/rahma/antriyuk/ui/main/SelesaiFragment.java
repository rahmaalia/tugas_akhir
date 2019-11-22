package com.rahma.antriyuk.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rahma.antriyuk.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class SelesaiFragment extends Fragment {

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selesai, container, false);
        Context mContext = getContext();


        return view;
    }

}