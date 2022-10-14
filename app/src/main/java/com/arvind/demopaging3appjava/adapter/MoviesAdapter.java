package com.arvind.demopaging3appjava.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.arvind.demopaging3appjava.data.MovieResponse;
import com.arvind.demopaging3appjava.databinding.ItemsMovieBinding;
import com.bumptech.glide.RequestManager;

import org.jetbrains.annotations.NotNull;

public class MoviesAdapter extends PagingDataAdapter<MovieResponse.Movie, MoviesAdapter.MovieViewHolder> {
    // Define Loading ViewType
    public static final int LOADING_ITEM = 0;
    // Define Movie ViewType
    public static final int MOVIE_ITEM = 1;
    RequestManager glide;
    public MoviesAdapter(@NotNull DiffUtil.ItemCallback<MovieResponse.Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Return MovieViewHolder
        return new MovieViewHolder(ItemsMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // Get current movie
        MovieResponse.Movie currentMovie = getItem(position);
        // Check for null
        if (currentMovie != null) {
            // Set Image of Movie using glide Library
            glide.load("https://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath())
                    .into(holder.movieItemBinding.imageViewMovie);

            // Set rating of movie
            holder.movieItemBinding.textViewRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }
    }

    @Override
    public int getItemViewType(int position) {
        // set ViewType
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder {
        // Define movie_item layout view binding
        ItemsMovieBinding movieItemBinding;

        public MovieViewHolder(@NonNull ItemsMovieBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            // init binding
            this.movieItemBinding = movieItemBinding;
        }
    }

}
