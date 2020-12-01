package cl.desafiolatam.moviesapp.movielist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import cl.desafiolatam.moviesapp.R;

public class MovieListViewHolder extends RecyclerView.ViewHolder {
    private final ConstraintLayout movieContainer;
    private final ImageView moviePoster;
    private final TextView movieTitle;
    private final TextView movieYear;
    public MovieListViewHolder(@NonNull View itemView) {
        super(itemView);
        movieContainer = itemView.findViewById(R.id.movieContainer);
        moviePoster = itemView.findViewById(R.id.moviePoster);
        movieTitle = itemView.findViewById(R.id.movieTitle);
        movieYear = itemView.findViewById(R.id.movieYear);
    }

    public ConstraintLayout getMovieContainer() {
        return movieContainer;
    }

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public TextView getMovieTitle() {
        return movieTitle;
    }

    public TextView getMovieYear() {
        return movieYear;
    }
}
