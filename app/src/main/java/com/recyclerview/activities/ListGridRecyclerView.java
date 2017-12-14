package com.recyclerview.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.recyclerview.R;
import com.recyclerview.adapter.ListGridAdapter;
import com.recyclerview.model.MovieModel;
import com.recyclerview.utils.CommonUtil;
import com.recyclerview.utils.decorator.GridSpacingItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListGridRecyclerView extends BaseActivity implements ListGridAdapter.OnItemClick {

    @BindView(R.id.rv_list_grid)
    RecyclerView rvListGrid;

    /**
     * Span constants
     */
    private int SPAN_1 = 1;
    private int SPAN_2 = 2;

    private Handler mHandler;
    private Runnable mRunnable;

    private MovieModel loadMoreModel;
    private ArrayList<MovieModel> mListMovie;
    private ListGridAdapter mListMovieAdapter;

    /**
     * Manges the spacing for the recycler view
     */
    private GridSpacingItemDecoration spacingItemDecoration;
    /**
     * Layout Manager for the product list recycler view
     */
    private GridLayoutManager gridLayoutManager;

    @Override
    public int getActivityView() {
        return R.layout.activity_recyclerview;
    }

    /**
     * Helper class to set span size for grid items based on orientation and device type
     */
    GridLayoutManager.SpanSizeLookup onSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            int viewType = mListMovieAdapter.getItemViewType(position);
            int spanCount;
            if (viewType == ListGridAdapter.ADAPTER_VIEW_LIST || viewType == ListGridAdapter.ADAPTER_VIEW_LOAD_MORE) {
                spanCount = SPAN_2;
            } else {
                spanCount = SPAN_1;
            }
            return spanCount;
        }
    };


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

            if (CommonUtil.needLoadMore(gridLayoutManager) && !mListMovie.contains(loadMoreModel)) {
                loadData();
            }
        }
    };

    @Override
    public void initializeComponents() {
        ButterKnife.bind(this);
        Context mContext = ListGridRecyclerView.this;

        loadMoreModel = new MovieModel();
        loadMoreModel.setViewType(ListGridAdapter.ADAPTER_VIEW_LOAD_MORE);

        mListMovie = new ArrayList<>();
        mListMovie.add(loadMoreModel);
        mListMovieAdapter = new ListGridAdapter(mContext, mListMovie, this);

        gridLayoutManager = new GridLayoutManager(this, SPAN_2);
        gridLayoutManager.setSpanSizeLookup(onSpanSizeLookup);

        rvListGrid.setLayoutManager(gridLayoutManager);

        int spacing = getResources().getDimensionPixelSize(R.dimen.dimen_10);
        spacingItemDecoration = new GridSpacingItemDecoration(SPAN_1, spacing, true);

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
     * Sets the view type (GRID | LIST)
     */
    private void setViewType(final int viewType) {
        if (mListMovieAdapter != null) {
            spacingItemDecoration.setSpanCount(viewType == ListGridAdapter.ADAPTER_VIEW_LIST ? SPAN_1 : SPAN_2);
            mListMovieAdapter.setViewType(viewType);
            mListMovieAdapter.notifyDataSetChanged();
        }
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

        // Construct an Intent as normal
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_PARAM_ID, mListMovie.get(position).getImage());

        // BEGIN_INCLUDE(start_activity)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_grid, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_list:
                setViewType(ListGridAdapter.ADAPTER_VIEW_LIST);
                break;
            case R.id.action_grid:
                setViewType(ListGridAdapter.ADAPTER_VIEW_GRID);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
