package com.booknara.android.apps.patterns.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.booknara.android.apps.patterns.R;
import com.booknara.android.apps.patterns.listener.OnItemClickListener;

import java.util.List;

public class SimpleArrayAdapter extends ArrayAdapter<String> {
    private static final String TAG = "SimpleArrayAdapter";

    private int resourceId;
    private OnItemClickListener listener;

    public SimpleArrayAdapter(Context context, int resource, List<String> objects, OnItemClickListener listener) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolderItem;

        if (convertView == null) {
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(resourceId, parent, false);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.textView = convertView.findViewById(R.id.main_item_text);

            convertView.setTag(viewHolderItem);
        } else {
            viewHolderItem = (ViewHolderItem) convertView.getTag();

        }

        String text = getItem(position);
        viewHolderItem.textView.setText(text);
        viewHolderItem.textView.setOnClickListener(v -> {
            listener.onClick(v, text);
        });

        return convertView;
    }

    private static class ViewHolderItem {
        private TextView textView;
    }
}
