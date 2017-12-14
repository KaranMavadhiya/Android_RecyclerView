package com.recyclerview.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

public class CommonUtil {

    private static boolean needLoadMore(int lastVisibleItemPosition, int totalItemCount) {
        final int max = (int) (totalItemCount * 0.8);
        return (lastVisibleItemPosition) >= max && lastVisibleItemPosition >= 0;
    }

    public static boolean needLoadMore(final GridLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }

    public static boolean needLoadMore(final LinearLayoutManager layoutManager) {
        // int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        // int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        return needLoadMore(lastVisibleItemPosition, totalItemCount);
    }
}
