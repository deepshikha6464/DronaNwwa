package com.deepshikha.nwwa.ui.home;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.deepshikha.nwwa.Main2Activity;
import com.deepshikha.nwwa.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

ImageView kala,saundrya;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        kala = root.findViewById(R.id.kala); kala.setOnClickListener(this);
        saundrya = root.findViewById(R.id.saundrya); saundrya.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), Main2Activity.class);

        int resId = v.getId();
        switch (resId){
            case R.id.kala:
                intent.putExtra("FragName","kala");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
            case R.id.saundrya:
                intent.putExtra("FragName","saundrya");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;
        }

    }
}