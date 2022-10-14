package com.arvind.demopaging3appjava.view;

import static com.arvind.demopaging3appjava.utils.Constants.API_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.arvind.demopaging3appjava.R;
import com.arvind.demopaging3appjava.adapter.MoviesAdapter;
import com.arvind.demopaging3appjava.adapter.MoviesLoadStateAdapter;
import com.arvind.demopaging3appjava.comparator.MovieComparator;
import com.arvind.demopaging3appjava.databinding.ActivityMainBinding;
import com.arvind.demopaging3appjava.utils.GridSpace;
import com.arvind.demopaging3appjava.viewmodel.MovieViewModel;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    MovieViewModel movieViewModel;
    MoviesAdapter moviesAdapter;
    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        initialize();
    }

    private void initialize() {
        if(API_KEY==null || API_KEY.isEmpty() ||API_KEY.equals("4795a0bf31c93bd7bf039ad5f11e65ec"))
            Toast.makeText(this, "ADD YOUR  API KEY", Toast.LENGTH_SHORT).show();

        // Create new MoviesAdapter object and provide
        moviesAdapter = new MoviesAdapter(new MovieComparator(),requestManager);
        // Create ViewModel
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        //set recyclerview and adapter
        initRecyclerviewAdapter();

        // Subscribe to to paging data
        movieViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            // submit new data to recyclerview adapter
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerviewAdapter() {
        // Create GridlayoutManger with span of count of 2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Finally set LayoutManger to recyclerview
        mBinding.rvMovies.setLayoutManager(gridLayoutManager);

        // Add ItemDecoration to add space between recyclerview items
        mBinding.rvMovies.addItemDecoration(new GridSpace(2, 20, true));

        // set adapter
        mBinding.rvMovies.setAdapter(
                // This will show end user a progress bar while pages are being requested from server
                moviesAdapter.withLoadStateFooter(
                        // When we will scroll down and next page request will be sent
                        // while we get response form server Progress bar will show to end user
                        new MoviesLoadStateAdapter(v -> {
                            moviesAdapter.retry();
                        }))
        );
        //
        //moviesAdapter.addLoadStateListener();
        // set Grid span to set progress at center
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // If progress will be shown then span size will be 1 otherwise it will be 2
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}