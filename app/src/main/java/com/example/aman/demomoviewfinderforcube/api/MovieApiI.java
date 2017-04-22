package com.example.aman.demomoviewfinderforcube.api;

import com.example.aman.demomoviewfinderforcube.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aman on 21-04-2017.
 */

public interface MovieApiI {

    @GET(".")
    Call<MovieModel> searchMovie(@Query("t") String title, @Query("type") String choice);
}
