package cl.desafiolatam.moviesapp.moviedetail;

import java.util.List;

import cl.desafiolatam.moviesapp.data.Repository;
import cl.desafiolatam.moviesapp.data.model.MovieDetail;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {
    protected MovieDetail movieDetail;
    private MovieDetailView view;
    private Repository repository;
    private static final String NOT_AVAILABLE = "No disponible";

    public MovieDetailPresenterImpl(Repository repository, MovieDetailView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadDataId(String imdbID) {
        view.showLoading();
        view.hideError();
        repository.getMovieDetail(new Repository.LoadDataCallBack() {
            @Override
            public void onMoviesResult(List<MovieResult> movies) {
                //no se utiliza en este contexto
            }

            @Override
            public void onDataNotAvailable() {
                view.stopLoading();
                view.showError();
            }

            @Override
            public void onMovieDetailResult(MovieDetail movieDetail) {
                MovieDetailPresenterImpl.this.movieDetail = movieDetail;
                view.stopLoading();
                view.hideError();
                view.showMovieDetail();
            }
        }, imdbID);
    }

    @Override
    public String getMoviePoster() {
        String movieUrl = movieDetail.getPoster();
        if (!movieUrl.isEmpty()) {
            return movieUrl;
        } else {
            return "";
        }
    }

    @Override
    public String getMovieTitle() {
        return movieDetail != null && !movieDetail.getTitle().isEmpty()
                ? movieDetail.getTitle()
                : NOT_AVAILABLE;
    }

    @Override
    public String getMovieYear() {
        return movieDetail != null && !movieDetail.getYear().isEmpty()
                ? "Año: " + movieDetail.getYear()
                : "Año: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieRestriction() {
        return movieDetail != null && !movieDetail.getRated().isEmpty()
                ? "Restricción: " + movieDetail.getRated()
                :"Restricción: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieGenre() {
        return movieDetail != null && !movieDetail.getGenre().isEmpty()
                ? "Género: " + movieDetail.getGenre()
                :"Género: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieRuntime() {
        return movieDetail != null && !movieDetail.getRuntime().isEmpty()
                ? "Duración: " + movieDetail.getRated()
                :"Duración: " + NOT_AVAILABLE;
    }

    @Override
    public String getIMDBRating() {
        return movieDetail != null && !movieDetail.getImdbID().isEmpty()
                ? "Rating IMDB: " + movieDetail.getImdbID()
                :"Rating IMDB: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieDirector() {
        return movieDetail != null && !movieDetail.getDirector().isEmpty()
                ? "Director: " + movieDetail.getDirector()
                :"Director: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieWriter() {
        return movieDetail != null && !movieDetail.getWriter().isEmpty()
                ? "Guión: " + movieDetail.getWriter()
                :"Guión: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieActors() {
        return movieDetail != null && !movieDetail.getActors().isEmpty()
                ? "Actores: " + movieDetail.getActors()
                :"Actores: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieSynopsis() {
        return movieDetail != null && !movieDetail.getPlot().isEmpty()
                ? "Sinopsis: " + movieDetail.getPlot()
                :"Sinopsis: " + NOT_AVAILABLE;
    }

    @Override
    public String getMovieProducers() {
        return movieDetail != null && !movieDetail.getProduction().isEmpty()
                ? "Productora: " + movieDetail.getProduction()
                :"Productora: " + NOT_AVAILABLE;
    }

    @Override
    public void clearData() {
        view = null;
        repository = null;
        movieDetail = null;
    }
}
