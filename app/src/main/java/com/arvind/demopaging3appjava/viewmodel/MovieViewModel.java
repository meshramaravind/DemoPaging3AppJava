package com.arvind.demopaging3appjava.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.arvind.demopaging3appjava.data.MovieResponse;
import com.arvind.demopaging3appjava.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    // Define Flowable for movies
    public Flowable<PagingData<MovieResponse.Movie>> moviePagingDataFlowable;

    public MovieViewModel() {
        init();
    }

    // Init ViewModel Data
    private void init() {
        // Define Paging Source
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        // Create new Pager
        Pager<Integer, MovieResponse.Movie> pager = new Pager(
                // Create new paging config
                new PagingConfig(20, //  Count of items in one page
                        20, //  Number of items to prefetch
                        false, // Enable placeholders for data which is not yet loaded
                        20, // initialLoadSize - Count of items to be loaded initially
                        20 * 499),// maxSize - Count of total items to be shown in recyclerview
                () -> moviePagingSource); // set paging source

        // inti Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);

    }
}