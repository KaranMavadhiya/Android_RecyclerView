package com.recyclerview.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.btn_vertical_recycler_view)
    Button VerticalRecyclerViewButton;

    @BindView(R.id.btn_horizontal_recycler_view)
    Button HorizontalRecyclerViewButton;

    @BindView(R.id.btn_list_grid_recycler_view)
    Button ListGridRecyclerViewButton;


    private View.OnClickListener customOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_vertical_recycler_view:
                    startActivity(VerticalRecyclerView.class);
                    break;
                case R.id.btn_horizontal_recycler_view:
                    startActivity(HorizontalRecyclerView.class);
                    break;
                case R.id.btn_list_grid_recycler_view:
                    startActivity(ListGridRecyclerView.class);
                    break;
            }
        }
    };

    @Override
    public int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeComponents() {
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        VerticalRecyclerViewButton.setOnClickListener(customOnClickListener);
        HorizontalRecyclerViewButton.setOnClickListener(customOnClickListener);
        ListGridRecyclerViewButton.setOnClickListener(customOnClickListener);
    }



}
