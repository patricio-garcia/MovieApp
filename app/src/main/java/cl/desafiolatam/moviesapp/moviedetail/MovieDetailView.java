package cl.desafiolatam.moviesapp.moviedetail;

public interface MovieDetailView {
    void showLoading();
    void stopLoading();
    void showMovieDetail();
    void showError();
    void hideError();
}
