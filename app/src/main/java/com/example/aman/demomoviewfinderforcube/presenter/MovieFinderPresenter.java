package com.example.aman.demomoviewfinderforcube.presenter;

import com.example.aman.demomoviewfinderforcube.api.MovieApiI;
import com.example.aman.demomoviewfinderforcube.model.MovieModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aman on 21-04-2017.
 */

public class MovieFinderPresenter {
    public static final String BASEURL = "http://www.omdbapi.com/";

    public void searchMovieByKeyword(final SerachMovieInterface serachMovieInterface, String keyword, String choice){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiI movieApiI = retrofit.create(MovieApiI.class);
        movieApiI.searchMovie(keyword, choice).enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                serachMovieInterface.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                serachMovieInterface.onError(t.getMessage());
            }
        });

    }

    public interface SerachMovieInterface {
        void onSuccess(MovieModel movieModel);
        void onError(String msg);
    }
}
