package com.example.aman.demomoviewfinderforcube;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aman.demomoviewfinderforcube.model.MovieModel;
import com.example.aman.demomoviewfinderforcube.presenter.MovieFinderPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Aman on 21-04-2017.
 */

public class MovieFinderFragment extends Fragment implements MovieFinderPresenter.SerachMovieInterface {

    EditText editText;
    ImageView imageView;
    RecyclerView recyclerView;
    ArrayList<MovieModel> movieModels;
    MovieAdapter movieAdapter;
    MovieFinderPresenter movieFinderPresenter;
    ProgressBar progressBar;
    TextView textViewNoItemFound;
    RadioGroup radioGroup;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        editText = (EditText) view.findViewById(R.id.edittext);
        imageView = (ImageView) view.findViewById(R.id.imageview_search);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        textViewNoItemFound = (TextView) view.findViewById(R.id.textview_noitem_found);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieModels = new ArrayList<>();
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        movieFinderPresenter = new MovieFinderPresenter();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice = "movie";
                String userInput = editText.getText().toString();
                if(!userInput.equals("")){
                    progressBar.setVisibility(View.VISIBLE);
                    if(radioGroup.getCheckedRadioButtonId() != R.id.radio1){
                        choice = "series";
                    }
                    movieFinderPresenter.searchMovieByKeyword(MovieFinderFragment.this,editText.getText().toString(), choice);
                }
            }
        });

        return view;
    }

    @Override
    public void onSuccess(MovieModel movieModel) {
        movieModels.clear();
        progressBar.setVisibility(View.GONE);
        if(movieModel.getTitle() == null) {
            textViewNoItemFound.setVisibility(View.VISIBLE);
            textViewNoItemFound.setText(getActivity().getResources().getString(R.string.textview_no_item_found));
        } else {
            textViewNoItemFound.setVisibility(View.GONE);
            movieModels.add(movieModel);
            movieAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(String msg) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.adpater_view_item, parent, false);

            return new MovieViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            holder.bindItem(movieModels.get(position));
        }

        @Override
        public int getItemCount() {
            return movieModels.size();
        }

        public class MovieViewHolder extends RecyclerView.ViewHolder{
            TextView textViewTitle, textViewGenere, textViewReleasedDate, textViewPlot, textViewRating;

            public MovieViewHolder(View view){
                super(view);
                textViewTitle = (TextView) view.findViewById(R.id.textview_title);
                textViewGenere = (TextView) view.findViewById(R.id.textview_genere);
                textViewReleasedDate = (TextView) view.findViewById(R.id.textview_releasedate);
                textViewPlot = (TextView) view.findViewById(R.id.plot);
                textViewRating = (TextView) view.findViewById(R.id.textview_rating);
            }

            public void bindItem(MovieModel movieModel){
                textViewTitle.setText(getActivity().getResources().getString(R.string.title) + " " + movieModel.getTitle());
                textViewRating.setText(movieModel.getRating());
                textViewPlot.setText(getActivity().getResources().getString(R.string.plot) + movieModel.getPlot());
                textViewReleasedDate.setText(getActivity().getResources().getString(R.string.date)+ " "  + movieModel.getReleasedate());
                textViewGenere.setText(getActivity().getResources().getString(R.string.genre) + " "  + movieModel.getGenere());
            }
        }
    }
}
