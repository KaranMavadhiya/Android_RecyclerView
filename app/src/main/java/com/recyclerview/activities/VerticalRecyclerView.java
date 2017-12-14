package com.recyclerview.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recyclerview.R;
import com.recyclerview.adapter.ListGridAdapter;
import com.recyclerview.model.MovieModel;
import com.recyclerview.utils.CommonUtil;
import com.recyclerview.utils.decorator.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerticalRecyclerView extends BaseActivity implements ListGridAdapter.OnItemClick {

    @BindView(R.id.rv_list_grid)
    RecyclerView rvListGrid;

    private Handler mHandler;
    private Runnable mRunnable;

    private MovieModel loadMoreModel;
    private ArrayList<MovieModel> mListMovie;
    private ListGridAdapter mListMovieAdapter;

    /**
     * Layout manager for product list recycler view
     */
    private LinearLayoutManager linearLayoutManager;
    /**
     * OnScrollListener to track to recycler scrolling for the pagination
     */
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (CommonUtil.needLoadMore(linearLayoutManager) && !mListMovie.contains(loadMoreModel)) {
                loadData();
            }
        }
    };

    @Override
    public int getActivityView() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void initializeComponents() {
        ButterKnife.bind(this);
        Context mContext = VerticalRecyclerView.this;

        loadMoreModel = new MovieModel();
        loadMoreModel.setViewType(ListGridAdapter.ADAPTER_VIEW_LOAD_MORE);

        mListMovie = new ArrayList<>();
        mListMovie.add(loadMoreModel);
        mListMovieAdapter = new ListGridAdapter(mContext, mListMovie, this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListGrid.setLayoutManager(linearLayoutManager);

        /*
         * Manges the spacing for the recycler view
         */
        int SPAN_1 = 1;
        int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_10);
        GridSpacingItemDecoration spacingItemDecoration = new GridSpacingItemDecoration(SPAN_1, spacing, true);
        rvListGrid.addItemDecoration(spacingItemDecoration);

        rvListGrid.setAdapter(mListMovieAdapter);
        rvListGrid.addOnScrollListener(onScrollListener);


        loadData();
    }

    private void loadData() {
        addLoadMore();
        mHandler = new Handler();
        mRunnable = new Runnable() {
            public void run() {

                generateList();

                removeLoadMore();
                mListMovieAdapter.notifyDataSetChanged();
                mHandler.removeCallbacks(mRunnable);
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }

    private void generateList() {


        MovieModel mMovieModel = new MovieModel();
        mMovieModel.setTitle("CHILDREN OF THE REVOLUTION");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513144831/RecyclerView/movie_children.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("X-MEN APOCALYPSE");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,r_0,w_400/v1513144831/RecyclerView/movie_xman.jpg");
        mListMovie.add(mMovieModel);


        mMovieModel = new MovieModel();
        mMovieModel.setTitle("TERMINATOR 5");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513144831/RecyclerView/image_terminator.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("BAHUBALI THE CONCLUTION");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513144833/RecyclerView/image_bahubali.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("RAMAYANA THE EPIC");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513144832/RecyclerView/image_ramayana.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("PUNCH RAJA");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513144830/RecyclerView/img_punch_raja.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("SULTAN");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513234967/RecyclerView/image_sultan.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("PK");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513234923/RecyclerView/image_pk.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("DUBANGG 3");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513234924/RecyclerView/image_dabangg3.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("TEASER");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513235248/RecyclerView/image_teaser.jpg");
        mListMovie.add(mMovieModel);

        mMovieModel = new MovieModel();
        mMovieModel.setTitle("EEGA");
        mMovieModel.setImage("http://res.cloudinary.com/reyinfotech/image/upload/c_scale,w_400/v1513235246/RecyclerView/image_eega.jpg");
        mListMovie.add(mMovieModel);

    }


    /**
     * Remove load more model
     */
    private void removeLoadMore() {
        if (mListMovie != null && mListMovie.contains(loadMoreModel)) {
            mListMovie.remove(loadMoreModel);
            mListMovieAdapter.notifyDataSetChanged();

        }
    }

    /**
     * Add load more model
     */
    private void addLoadMore() {
        if (mListMovie != null && !mListMovie.contains(loadMoreModel)) {
            mListMovie.add(loadMoreModel);

            Handler handler = new Handler();
            Runnable r = new Runnable() {
                public void run() {
                    mListMovieAdapter.notifyDataSetChanged();
                }
            };
            handler.post(r);
        }
    }

    @Override
    public void onImageClick(View view, int position) {

        /* Construct an Intent as normal */
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_PARAM_ID, mListMovie.get(position).getImage());

        /* BEGIN_INCLUDE(start_activity) */
        /*
          Now create an {@link android.app.ActivityOptions} instance using the
          {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity, Pair[])} factory
          method.
         */
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                // Now we provide a list of Pair items which contain the view we can transitioning
                // from, and the name of the view it is transitioning to, in the launched activity
                new Pair<>(view.findViewById(R.id.iv_image), ImageActivity.VIEW_NAME_HEADER_IMAGE));
        // Now we can start the Activity, providing the activity options as a bundle
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }
}
