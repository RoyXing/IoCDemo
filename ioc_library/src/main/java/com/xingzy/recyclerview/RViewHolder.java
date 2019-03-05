package com.xingzy.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    private View currentView;
    private SparseArray<View> views;

    public RViewHolder(@NonNull View itemView) {
        super(itemView);
        this.currentView = itemView;
        views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = currentView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    static RViewHolder getViewHolder(ViewGroup viewGroup, int layoutId) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        return new RViewHolder(view);
    }

    View getCurrentView() {
        return currentView;
    }
}
