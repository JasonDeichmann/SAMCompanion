
package com.example.kayletiu.samcompanion;
import android.content.Context;

import java.io.FileOutputStream;
        import android.app.Activity;
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
        import android.support.v7.widget.helper.ItemTouchHelper;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

import com.example.kayletiu.samcompanion.AddPost;
import com.example.kayletiu.samcompanion.MyDividerItemDecoration;
import com.example.kayletiu.samcompanion.Post;
import com.example.kayletiu.samcompanion.PostAdapter;
import com.example.kayletiu.samcompanion.R;

import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.InputStreamReader;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Collections;
        import java.util.Date;
        import java.util.List;

        import static android.app.Activity.RESULT_OK;
        import static java.util.Collections.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton fab;
    private List<Post> postList = new ArrayList<>();
    private RecyclerView recyclerView;

    private SharedPreferences preferencesSettings;
    private SharedPreferences.Editor preferencesEditor;
    private PostAdapter mAdapter;
    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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

    public void populateData(){
        postList.add(new Post(2,"goofy","Is it anxiety?","I always feel the butterflies in my stomach " +
                "everytime I see my crush. Is it anxiety? What should I do.",null,"09-07-2018"));
        postList.add(new Post(3,"duck","I am so so so sad?","I bond with my friends, I play different games and sports " +
                "but yet, I feel incomplete. What am I feeling? Please help.",null,"09-07-2018"));
        postList.add(new Post(4,"mouse","Message to all","We are here whenever you want someone to talk with " +
                "about your problems ",null,"09-07-2018"));


        preferencesEditor = preferencesSettings.edit();
        preferencesEditor.putBoolean("isInitialized",true);
        preferencesEditor.apply();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        fab = view.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getActivity(),AddPost.class);
                startActivityForResult(i1,0);
            }
        });


        postList = new ArrayList<Post>();



        preferencesSettings = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        preferencesSettings = getContext().getSharedPreferences("MySettings", Context.MODE_PRIVATE);

        boolean init = preferencesSettings.getBoolean("isInitialized", false);
        if(!init){

            populateData();
        }
        else {
            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader br;
            try {

                fis = getContext().openFileInput("homePosts.txt");
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                int id = Integer.parseInt(br.readLine());
                String author = br.readLine();
                String title = br.readLine();
                String content = br.readLine();
                String date = br.readLine();
                while (author != null && content != null) {
                    postList.add(new Post(id, author, title, content, null, date));
                    id = Integer.parseInt(br.readLine());
                    author = br.readLine();
                    title = br.readLine();
                    content = br.readLine();
                    date = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        // Inflate the layout for this fragment



        //wew

        recyclerView = (RecyclerView) view.findViewById(R.id.post_recylerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
//        recyclerView.addOnItemTouchListener(
//            new RecyclerItemClickListener(this.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                @Override public void onItemClick(View view, int position) {
//                    Post post = postList.get(position);
//                    Intent intent = new Intent(getContext(), ReplyPost.class);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("author", post.getAuthor());
//                    bundle.putString("title", post.getTitle());
//                    bundle.putString("content", post.getContent());
//                    bundle.putString("datePosted", post.getDatePosted());
//                    bundle.putInt("id", post.getId());
//                    bundle.putInt("position", position);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, 10);
//                }
//
//            })
//        );


        return view;


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
            int latestId = postList.get(0).getId();

            for(int i = 0; i < postList.size(); i++){
                if(postList.get(i).getId() > latestId){
                    latestId = postList.get(i).getId();
                }
            }
            String author = "doggy";
            String content = b1.getString("content");
            String title = b1.getString("title");
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String date = df.format(c);
            postList.add(new Post(latestId + 1,author,title,content,null,date));
            mAdapter.notifyItemInserted(postList.size() - 1);
            Log.d("ADD", "POST IS ADDED");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        String filename = "homePosts.txt";
        Log.d("FUCK",postList.size()+"");
        File file = null;
        FileOutputStream outputStream = null;
        try{
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            file = getContext().getFilesDir();
            for(int i = 0; i < postList.size(); i++){
                outputStream.write((postList.get(i).getId() + "\n").getBytes());
                outputStream.write((postList.get(i).getAuthor() + "\n").getBytes());
                outputStream.write((postList.get(i).getTitle() + "\n").getBytes());
                outputStream.write((postList.get(i).getContent() + "\n").getBytes());
                outputStream.write((postList.get(i).getDatePosted() + "\n").getBytes());
            }


            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onDestroy();
    }
    @Override
    public void onPause() {
        String filename = "homePosts.txt";
        File file = null;
        FileOutputStream outputStream = null;
        try{
            outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
            file = getContext().getFilesDir();
            for(int i = 0; i < postList.size(); i++){
                outputStream.write((postList.get(i).getId() + "\n").getBytes());
                outputStream.write((postList.get(i).getAuthor() + "\n").getBytes());
                outputStream.write((postList.get(i).getTitle() + "\n").getBytes());
                outputStream.write((postList.get(i).getContent() + "\n").getBytes());
                outputStream.write((postList.get(i).getDatePosted() + "\n").getBytes());
            }


            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
    }
}
