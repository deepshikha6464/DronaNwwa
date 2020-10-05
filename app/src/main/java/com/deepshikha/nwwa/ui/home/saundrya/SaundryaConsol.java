package com.deepshikha.nwwa.ui.home.saundrya;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepshikha.nwwa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaundryaConsol extends AppCompatActivity {


    public SaundryaConsol() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_saundrya_consol);
    }

//    @Override
//    public View onCreate(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_saundrya_consol, container, false);
//    }

}
