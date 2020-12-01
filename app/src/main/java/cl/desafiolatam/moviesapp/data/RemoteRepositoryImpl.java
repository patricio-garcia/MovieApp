package cl.desafiolatam.moviesapp.data;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public class RemoteRepositoryImpl implements Repository {

    private final List<MovieResult> movieResults;
    private final Map<String, MovieDetail> movieDetailMap;
    private static final int DELAY = 3000;

    public RemoteRepositoryImpl(List<MovieResult> movieResults, Map<String, MovieDetail> movieDetailMap) {
        this.movieResults = movieResults;
        this.movieDetailMap = movieDetailMap;
    }

    @Override
    public void getMovieList(LoadDataCallBack callBack) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> callBack.onMoviesResult(new ArrayList<>(movieResults)), DELAY);
    }

    @Override
    public void getMovieDetail(LoadDataCallBack callBack, String imdbID) {
        Handler handler = new Handler(Looper.getMainLooper());
        MovieDetail movieDetail = movieDetailMap.get(imdbID);
        handler.postDelayed(() -> {
            if (movieDetail != null) {
                callBack.onMovieDetailResult(movieDetail);
            } else {
                callBack.onDataNotAvailable();
            }
        }, DELAY);
    }
}
