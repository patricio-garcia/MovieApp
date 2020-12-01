package cl.desafiolatam.moviesapp.movielist;

public interface MovieListView {
    void showLoading();
    void stopLoading();
    void showList();
    void showError();
    void hideList();
    void hideError();
}
