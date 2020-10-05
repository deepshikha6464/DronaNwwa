package com.deepshikha.nwwa.ui.samudri;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepshikha.nwwa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SamudriItems extends Fragment {


    public SamudriItems() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_samudri_items, container, false);
    }

}
