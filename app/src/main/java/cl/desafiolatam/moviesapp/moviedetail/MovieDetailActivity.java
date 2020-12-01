package cl.desafiolatam.moviesapp.moviedetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import cl.desafiolatam.moviesapp.R;
import cl.desafiolatam.moviesapp.data.DataLoaderUtils;
import cl.desafiolatam.moviesapp.data.RemoteRepositoryImpl;
import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    public static final String MOVIE_IMDB_ID = "movieIMDBId";
    private MovieDetailPresenter presenter;
    private ProgressBar progressBar;
    private TextView movieError;
    private ImageView moviePoster;
    private TextView movieName;
    private TextView movieYear;
    private TextView movieRating;
    private TextView movieGenre;
    private TextView movieRuntime;
    private TextView movieIMDBRating;
    private TextView movieDirector;
    private TextView movieWriters;
    private TextView movieActors;
    private TextView movieSynopsis;
    private TextView movieProduction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        progressBar = findViewById(R.id.progress);
        movieError = findViewById(R.id.movieDetailError);
        moviePoster = findViewById(R.id.moviePoster);
        movieName = findViewById(R.id.movieTitle);
        movieYear = findViewById(R.id.movieYear);
        movieRating = findViewById(R.id.movieRating);
        movieGenre = findViewById(R.id.movieGenre);
        movieRuntime = findViewById(R.id.movieRuntime);
        movieIMDBRating = findViewById(R.id.movieImdbRaiting);
        movieDirector = findViewById(R.id.movieDirector);
        movieWriters = findViewById(R.id.movieWriters);
        movieActors = findViewById(R.id.movieActors);
        movieSynopsis = findViewById(R.id.movieSynopsis);
        movieProduction = findViewById(R.id.movieProduction);
        List<MovieResult> results = DataLoaderUtils.readMovies(getAssets());
        Map<String, MovieDetail> movieDetailMap = DataLoaderUtils.readAllMovieDetails(getAssets());
        presenter = new MovieDetailPresenterImpl(new RemoteRepositoryImpl(results, movieDetailMap),this);
        String imdbId = getIntent().getStringExtra(MOVIE_IMDB_ID);
        presenter.loadDataId(imdbId);
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
    public void showMovieDetail() {
        Glide.with(this)
                .load(presenter.getMoviePoster())
                .placeholder(R.drawable.ic_movie_open_outline)
                .into(moviePoster);
        movieName.setText(presenter.getMovieTitle());
        movieYear.setText(presenter.getMovieYear());
        movieRating.setText(presenter.getMovieRestriction());
        movieGenre.setText(presenter.getMovieGenre());
        movieRuntime.setText(presenter.getMovieRuntime());
        movieIMDBRating.setText(presenter.getIMDBRating());
        movieDirector.setText(presenter.getMovieDirector());
        movieWriters.setText(presenter.getMovieWriter());
        movieActors.setText(presenter.getMovieActors());
        movieSynopsis.setText(presenter.getMovieSynopsis());
        movieProduction.setText(presenter.getMovieProducers());
    }

    @Override
    public void showError() {
        movieError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        movieError.setVisibility(View.GONE);
    }
}