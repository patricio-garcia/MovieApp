package cl.desafiolatam.moviesapp.moviedetail;

public interface MovieDetailPresenter {
    void loadDataId(String imdbID);
    String getMoviePoster();
    String getMovieTitle();
    String getMovieYear();
    String getMovieRestriction();
    String getMovieGenre();
    String getMovieRuntime();
    String getIMDBRating();
    String getMovieDirector();
    String getMovieWriter();
    String getMovieActors();
    String getMovieSynopsis();
    String getMovieProducers();
    void clearData();
}
