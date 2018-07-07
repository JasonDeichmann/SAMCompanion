package com.example.kayletiu.samcompanion;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mharjorie Sandel on 07/07/2018.
 */

public class Community extends Fragment {
    public static Community newInstance() {
        Community fragment = new Community();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.community_layout, container, false);
        getActivity().setTitle("Home");


        return view;
    }
}