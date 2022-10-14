package com.arvind.demopaging3appjava.paging;

import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.arvind.demopaging3appjava.data.MovieResponse;
import com.arvind.demopaging3appjava.webapi.ApiClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviePagingSource extends RxPagingSource<Integer, MovieResponse.Movie> {

    @NotNull
    @Override
    public Single<LoadResult<Integer, MovieResponse.Movie>> loadSingle(@NotNull LoadParams<Integer> loadParams) {
        try {
            // If page number is already there then init page variable with it otherwise we are loading fist page
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
            // Send request to server with page number
            return ApiClient.getApiService()
                    .getMoviesByPage(page)
                    // Subscribe the result
                    .subscribeOn(Schedulers.io())
                    // Map result top List of movies
                    .map(MovieResponse::getResults)
                    // Map result to LoadResult Object
                    .map(movies -> toLoadResult(movies, page))
                    // when error is there return error
                    .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            // Request ran into error return error
            return Single.just(new LoadResult.Error(e));
        }
    }

    // Method to map Movies to LoadResult object
    private LoadResult<Integer, MovieResponse.Movie> toLoadResult(List<MovieResponse.Movie> movies, int page) {
        return new LoadResult.Page(movies, page == 1 ? null : page - 1, page + 1);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, MovieResponse.Movie> pagingState) {
        return null;
    }
}
