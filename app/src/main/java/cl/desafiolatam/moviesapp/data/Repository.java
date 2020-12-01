package cl.desafiolatam.moviesapp.data;

import java.util.List;

import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public interface Repository {
    void getMovieList(LoadDataCallBack callBack);
    void getMovieDetail(LoadDataCallBack callBack, String imdbID);
    interface LoadDataCallBack {
        void onMoviesResult(List<MovieResult> movies);
        void onDataNotAvailable();
        void onMovieDetailResult(MovieDetail movieDetail);
    }
}
