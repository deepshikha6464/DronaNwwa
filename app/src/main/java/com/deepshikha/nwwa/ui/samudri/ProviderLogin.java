package com.deepshikha.nwwa.ui.samudri;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.deepshikha.nwwa.R;
import com.deepshikha.nwwa.ui.home.saundrya.SaundryaConsol;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderLogin extends Fragment implements View.OnClickListener {
    private static final String TAG = "ProviderLogin";

    Button Login,Register;
    Spinner spinner;
    EditText description;
    TextInputEditText code;
    public ProviderLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_providier_login, container, false);
        Login = view.findViewById(R.id.providerLogin);  Login.setOnClickListener(this);
        Register = view.findViewById(R.id.providerReg);  Register.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.providerLogin:
//                match code for saundrya and kala
                Intent intent = new Intent(getActivity(), SaundryaConsol.class);
startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                break;

        case R.id.providerReg:
                break;
        }
    }
}
