package cl.desafiolatam.moviesapp.movielist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cl.desafiolatam.moviesapp.R;
import cl.desafiolatam.moviesapp.data.model.MovieResult;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {
    private final List<MovieResult> movieList = new ArrayList<>();
    private final MovieListClickListener listener;

    public MovieListAdapter(List<MovieResult> movieList, MovieListClickListener listener) {
        this.movieList.addAll(movieList);
        this.listener = listener;
    }

    public void setMovieList(List<MovieResult> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_movie_list_item, parent, false);
        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        MovieResult data = movieList.get(position);
        holder.getMovieContainer().setOnClickListener(view -> listener.onItemClick(data));
        holder.getMovieTitle().setText(data.getTitle());
        holder.getMovieYear().setText(data.getYear());
        if (!data.getPoster().isEmpty()) {
            Glide.with(holder.itemView).load(data.getPoster()).placeholder(R.drawable.ic_movie_open_outline).into(holder.getMoviePoster());
        } else {
            Glide.with(holder.itemView).load(R.drawable.ic_movie_open_outline).into(holder.getMoviePoster());
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
