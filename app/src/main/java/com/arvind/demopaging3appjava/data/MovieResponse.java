package com.arvind.demopaging3appjava.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return movies;
    }

    public void setResults(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public class Movie {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public Double getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Double voteAverage) {
            this.voteAverage = voteAverage;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            return false;
        }
    }
}
