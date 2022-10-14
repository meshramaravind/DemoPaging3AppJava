package com.arvind.demopaging3appjava.comparator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.arvind.demopaging3appjava.data.MovieResponse;

public class MovieComparator extends DiffUtil.ItemCallback<MovieResponse.Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull MovieResponse.Movie oldItem, @NonNull MovieResponse.Movie newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieResponse.Movie oldItem, @NonNull MovieResponse.Movie newItem) {
        return oldItem.getId().equals(newItem.getId());
    }
}