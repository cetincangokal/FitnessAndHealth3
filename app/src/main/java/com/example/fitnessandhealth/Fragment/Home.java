package com.example.fitnessandhealth.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.fitnessandhealth.FitnessActivity;
import com.example.fitnessandhealth.healthActivity;
import com.example.fitnessandhealth.R;


public class Home extends Fragment {
    ImageButton fitnessBtn,healthBtn;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        fitnessBtn = (ImageButton) v.findViewById(R.id.fitness_imageBTN);
        healthBtn = (ImageButton) v.findViewById(R.id.health_imageBTN);

        fitnessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FitnessActivity.class);
                startActivity(i);
            }
        });
        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), healthActivity.class);
                startActivity(i);
            }
        });


        return v;

    }
}