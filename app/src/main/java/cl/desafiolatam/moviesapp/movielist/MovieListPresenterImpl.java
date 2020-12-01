package cl.desafiolatam.moviesapp.movielist;

import java.util.ArrayList;
import java.util.List;

import cl.desafiolatam.moviesapp.data.Repository;
import cl.desafiolatam.moviesapp.data.Repository.LoadDataCallBack;
import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public class MovieListPresenterImpl implements MovieListPresenter {
    private Repository repository;
    private MovieListView view;
    private List<MovieResult> movieResults = new ArrayList<>();

    public MovieListPresenterImpl(Repository repository, MovieListView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadMovies() {
        view.showLoading();
        view.hideError();
        repository.getMovieList(new LoadDataCallBack() {
            @Override
            public void onMoviesResult(List<MovieResult> movies) {
                view.stopLoading();
                if (!movies.isEmpty()) {
                    movieResults.clear();
                    movieResults.addAll(movies);
                    view.showList();
                    view.hideError();
                } else {
                    view.hideList();
                    view.showError();
                }
            }

            @Override
            public void onDataNotAvailable() {
                view.stopLoading();
                view.hideList();
                view.showError();
            }

            @Override
            public void onMovieDetailResult(MovieDetail movieDetail) {
                //no se usa en este contexto
            }
        });
    }

    @Override
    public List<MovieResult> getMovies() {
        if (movieResults != null) {
            return new ArrayList<>(movieResults);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void clearData() {
        view = null;
        repository = null;
        movieResults = null;
    }
}
