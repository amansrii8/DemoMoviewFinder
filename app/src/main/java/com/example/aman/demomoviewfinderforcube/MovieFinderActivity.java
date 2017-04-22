package com.example.aman.demomoviewfinderforcube;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MovieFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_finder);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MovieFinderFragment movieFinderFragment = new MovieFinderFragment();
        fragmentManager.beginTransaction().replace(R.id.layout_container, movieFinderFragment).commit();
    }
}
