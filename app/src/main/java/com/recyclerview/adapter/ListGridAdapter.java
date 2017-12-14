package com.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.recyclerview.R;
import com.recyclerview.model.MovieModel;
import com.recyclerview.utils.imageloader.ImageLoader;

import java.util.ArrayList;

/**
 * Holds the view for Product List
 */
public class ListGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*
     * Now here we are giving user an option to change to List item to Grid item.
     * For that we are dividing the default view_type from [0] to [1 & 2]
     */
    public static final int ADAPTER_VIEW_LIST = 1;// This is the sub view_type for default view_type = 0, It'll be used to display items in List
    public static final int ADAPTER_VIEW_GRID = 2;//This is the sub view_type for default view_type = 0, It'll be used to display items in two columns grid
    /*
     * This is the load more view_type, It'll be used to display load more indicator
     */
    public final static int ADAPTER_VIEW_LOAD_MORE = 3;
    /*
     * This is the default view_type for ProductModel
     * Here, We are considering that default view is the original row item to be displayed in adapter
     */
    private final static int ADAPTER_VIEW_DEFAULT = 0;
    private Context context;
    private ArrayList<MovieModel> modelArrayList;
    private OnItemClick onItemClick;

    private int viewType = ADAPTER_VIEW_LIST;


    public ListGridAdapter(final Context context, ArrayList<MovieModel> productList, OnItemClick onItemClick) {
        this.modelArrayList = productList;
        this.context = context;
        this.onItemClick = onItemClick;
    }

    public void setViewType(final int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        switch (modelArrayList.get(position).getViewType()) {

            case ADAPTER_VIEW_DEFAULT:
                return viewType;

            default:
                return modelArrayList.get(position).getViewType();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType) {
            case ADAPTER_VIEW_LIST:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
                return new ItemViewHolder(itemView);
            case ADAPTER_VIEW_GRID:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);
                return new ItemViewHolder(itemView);
            case ADAPTER_VIEW_LOAD_MORE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_load_more, parent, false);
                return new LoadingViewHolder(itemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setId(position);

        MovieModel model = modelArrayList.get(position);

        switch (model.getViewType()) {
            case ADAPTER_VIEW_DEFAULT:
                ItemViewHolder mItemViewHolder = (ItemViewHolder) holder;

                ImageLoader.loadImage(context, mItemViewHolder.image, model.getImage(), R.drawable.place_holder_square);

                mItemViewHolder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClick != null) {
                            onItemClick.onImageClick(view,position);
                        }
                    }
                });


                break;

            case ADAPTER_VIEW_LOAD_MORE:

                break;

            default:
                throw new IllegalArgumentException("Unrecognized item type: " + model.getViewType());
        }
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }


    public interface OnItemClick {
        void onImageClick(View view, int position);
    }

    /**
     * item view holder
     */
    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        ItemViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.iv_image);
        }
    }

    /**
     * Loading indicator view holder
     */
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        private LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

}