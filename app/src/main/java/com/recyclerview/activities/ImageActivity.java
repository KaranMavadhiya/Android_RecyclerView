package com.recyclerview.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.recyclerview.R;
import com.recyclerview.utils.LogUtil;
import com.recyclerview.utils.imageloader.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ImageActivity extends BaseActivity {

    private Context mContext;
    private String TAG = getClass().getSimpleName();

    // Extra name for the ID parameter
    public static final String EXTRA_PARAM_ID = "image:_uri";
    // View name of the header image. Used for activity scene transitions
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";

    @BindView(R.id.iv_image)
    ImageView imageHeader;



    @Override
    public int getActivityView() {
        return R.layout.activity_image;
    }

    @Override
    public void initializeComponents() {
        ButterKnife.bind(this);
        mContext = ImageActivity.this;

        // Retrieve the correct Item instance, using the ID provided in the Intent
        String imagePath = getIntent().getStringExtra(EXTRA_PARAM_ID);
        ViewCompat.setTransitionName(imageHeader, VIEW_NAME_HEADER_IMAGE);
        if (imagePath != null && imagePath.length() > 0) {
            LogUtil.d(TAG, "imagePath = " + imagePath);
            ImageLoader.loadImage(mContext, imageHeader, imagePath, R.drawable.place_holder_landscape);
        }

    }
}
