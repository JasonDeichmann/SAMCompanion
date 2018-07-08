package com.example.kayletiu.samcompanion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Quotes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Quotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Quotes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private QuotesAdapter qAdapter;
    private List<Quote> quoteList = new ArrayList<>();
    private RecyclerView recyclerView;

    private FloatingActionButton fab;


    private SharedPreferences preferencesSettings;
    private SharedPreferences.Editor preferencesEditor;


    public Quotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Quotes.
     */
    // TODO: Rename and change types and number of parameters
    public static Quotes newInstance(String param1, String param2) {
        Quotes fragment = new Quotes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);

        preferencesSettings = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        preferencesSettings = getContext().getSharedPreferences("MySettings", Context.MODE_PRIVATE);

        boolean init = preferencesSettings.getBoolean("isInitializedQuotes", false);
        if(!init){

            populateQuotes();
        }
        else {

            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader br;
            try {

                fis = getContext().openFileInput("quotes.txt");
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String user = br.readLine();
                String content = br.readLine();
                String author = br.readLine();
                while (author != null && content != null) {
                    quoteList.add(new Quote(user, content, author));
                    user = br.readLine();
                    content = br.readLine();
                    author = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        fab = view.findViewById(R.id.addNewQuote);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddQuote.class);
                startActivityForResult(intent, 20);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.quotes_recycleView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        qAdapter = new QuotesAdapter(quoteList);
        recyclerView.setAdapter(qAdapter);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));



        return view;
    }

    public void populateQuotes(){
        quoteList.add(new Quote("goofy", "\"Once you choose hope, anything is possible.\"","Christopher Reeve"));
        quoteList.add(new Quote("mickey", "\"Maybe you have to know the darkness before you can appreciate the light.\"", "Madeleine L'Engle"));
        quoteList.add(new Quote("donald", "\"A positive attitude gives you power over your circumstances instead of your circumstances having power over you.\"","Joyce Meyer"));
        quoteList.add(new Quote("minnie", "\"What the caterpillar calls the end of the world, the master calls a butterfly.\"" ,"Richard Bach"));
        quoteList.add(new Quote("daisy", "\"Keep yourself busy if you want to avoid depression. For me, inactivity is the enemy.\"","Matt Lucas"));


        preferencesEditor = preferencesSettings.edit();
        preferencesEditor.putBoolean("isInitializedQuotes",true);
        preferencesEditor.apply();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Bundle b1 =  data.getExtras();

            String user = "doggy";
            String content = b1.getString("quoteContent");
            String author = b1.getString("quoteAuthor");
            quoteList.add(new Quote(user,"\""+ content + "\"", author));
            qAdapter.notifyItemInserted(quoteList.size() - 1);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        String filename = "quotes.txt";
        File file = null;
        FileOutputStream outputStream = null;
        try{
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            file = getContext().getFilesDir();
            for(int i = 0; i < quoteList.size(); i++){
                outputStream.write((quoteList.get(i).getUser() + "\n").getBytes());
                outputStream.write((quoteList.get(i).getContent() + "\n").getBytes());
                outputStream.write((quoteList.get(i).getAuthor() + "\n").getBytes());

            }


            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }
    @Override
    public void onPause() {
        String filename = "quotes.txt";
        File file = null;
        FileOutputStream outputStream = null;
        try{
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            file = getContext().getFilesDir();
            for(int i = 0; i < quoteList.size(); i++){
                outputStream.write((quoteList.get(i).getUser() + "\n").getBytes());
                outputStream.write((quoteList.get(i).getContent() + "\n").getBytes());
                outputStream.write((quoteList.get(i).getAuthor() + "\n").getBytes());
            }
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
    }
}
