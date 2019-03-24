package com.example.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdesc();
    }


    public void initdesc(){
        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Kale/Lemon Sandwiches. This sandwich is stunningly delicious and tastes as good as it is healthy. Any grens of choice will work. the lemon adds on almost sweeet taste. Be generous with id!");
        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Mango-Lime Bean Salad. Everyone loves, so double or even  triple the recipe! it vanishes in a flash.");
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Sweet Potato. this soup is so good Essy and i ate it all the first time i made it. Any grens of choice will work. the lemon adds on almost sweeet taste. Be generous with id!");
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Lime Mousse. This is fabulous alone, topped with fruit of ay kind or as a frosting on cake. Any grens of choice will work. the lemon adds on almost sweeet taste. Be generous with id!");
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Sweet Potato. this soup is so good Essy and i ate it all the first time i made it. Any grens of choice will work. the lemon adds on almost sweeet taste. Be generous with id!");
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Lime Mousse. This is fabulous alone, topped with fruit of ay kind or as a frosting on cake. Any grens of choice will work. the lemon adds on almost sweeet taste. Be generous with id!");
        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
