package cl.desafiolatam.moviesapp.movielist;

import java.util.List;

import cl.desafiolatam.moviesapp.data.model.MovieResult;

public interface MovieListPresenter {
    void loadMovies();
    List<MovieResult> getMovies();
    void clearData();
}
