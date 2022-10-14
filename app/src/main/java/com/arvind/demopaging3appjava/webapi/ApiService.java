package com.arvind.demopaging3appjava.webapi;

import com.arvind.demopaging3appjava.data.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    // Define Get request with query string parameter as page number
    @GET("movie/popular")
    Single<MovieResponse> getMoviesByPage(@Query("page") int page);
}
