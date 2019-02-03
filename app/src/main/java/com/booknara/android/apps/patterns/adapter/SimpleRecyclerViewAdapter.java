package com.booknara.android.apps.patterns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.listener.OnItemClickListener;
import com.booknara.android.apps.patterns.model.HeaderModel;
import com.booknara.android.apps.patterns.model.ModelType;
import com.booknara.android.apps.patterns.model.PhoneticModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "SimpleRecyclerViewAdapter";

    private Context context;
    private List<ModelType> data;
    private OnItemClickListener listener;

    public SimpleRecyclerViewAdapter(List<ModelType> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case ModelType.HEADER:
                viewHolder = new HeaderViewHolder(
                        (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false));
                break;
            case ModelType.BODY:
                viewHolder = new BodyViewHolder(
                        (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item, parent, false));
                break;
             default:
                 throw new IllegalArgumentException("invalid model type");
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ModelType type = data.get(position);
        switch (type.getItemType()) {
            case ModelType.HEADER:
                ((HeaderViewHolder) viewHolder).headerView.setText(((HeaderModel)type).getHeader());
                break;
            case ModelType.BODY:
                ((BodyViewHolder) viewHolder).textView.setText(((PhoneticModel)type).getText());
                ((BodyViewHolder) viewHolder).textView.setOnClickListener(v -> listener.onClick(v, type));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getItemType();
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerView;
        public HeaderViewHolder(@NonNull TextView headerView) {
            super(headerView);
            this.headerView = headerView;
        }
    }

    private static class BodyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public BodyViewHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
