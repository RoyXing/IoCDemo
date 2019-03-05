package com.xingzy.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author roy.xing
 * @date 2019/3/5
 */
public abstract class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private List<T> datas;
    private RView.OnItemClickListener<T> onItemClickListener;
    private RView.OnItemLongClickListener<T> onItemLongClickListener;

    public RViewAdapter(List<T> datas) {
        if (datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
    }

    public abstract int getLayoutId();

    public abstract void convert(RViewHolder holder, T t);

    public void setOnItemClickListener(RView.OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RView.OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        RViewHolder holder = RViewHolder.getViewHolder(viewGroup, getLayoutId());
        setListener(holder);
        return holder;
    }

    private void setListener(final RViewHolder holder) {
        holder.getCurrentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                if (onItemClickListener != null) {
                    if (position != -1) {
                        onItemClickListener.onItemClick(v, datas.get(position), position);
                    }
                }
            }
        });

        holder.getCurrentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getLayoutPosition();
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(v, datas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder rViewHolder, int i) {
        convert(rViewHolder, datas.get(i));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
