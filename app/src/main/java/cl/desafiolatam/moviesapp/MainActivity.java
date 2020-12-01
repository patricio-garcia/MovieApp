package cl.desafiolatam.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cl.desafiolatam.moviesapp.data.DataLoaderUtils;
import cl.desafiolatam.moviesapp.data.RemoteRepositoryImpl;
import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;
import cl.desafiolatam.moviesapp.moviedetail.MovieDetailActivity;
import cl.desafiolatam.moviesapp.movielist.MovieListAdapter;
import cl.desafiolatam.moviesapp.movielist.MovieListClickListener;
import cl.desafiolatam.moviesapp.movielist.MovieListPresenter;
import cl.desafiolatam.moviesapp.movielist.MovieListPresenterImpl;
import cl.desafiolatam.moviesapp.movielist.MovieListView;

public class MainActivity extends AppCompatActivity implements MovieListView, MovieListClickListener {
    private MovieListPresenter presenter;
    private ProgressBar progressBar;
    private RecyclerView movieList;
    private TextView movieLoadingError;
    private MovieListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        progressBar = findViewById(R.id.progressBar);
        movieLoadingError = findViewById(R.id.movieError);
        movieList = findViewById(R.id.movieList);
        List<MovieResult> results = DataLoaderUtils.readMovies(getAssets());
        Map<String, MovieDetail> movieDetailMap = DataLoaderUtils.readAllMovieDetails(getAssets());
        presenter = new MovieListPresenterImpl(new RemoteRepositoryImpl(results, movieDetailMap), this);
        adapter = new MovieListAdapter(presenter.getMovies(), this);
        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(adapter);
        presenter.loadMovies();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearData();
        presenter = null;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showList() {
        adapter.setMovieList(presenter.getMovies());
        movieList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        movieLoadingError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        movieList.setVisibility(View.GONE);
    }

    @Override
    public void hideError() {
        movieLoadingError.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(MovieResult movieResult) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_IMDB_ID, movieResult.getImdbID());
        startActivity(intent);
    }
}