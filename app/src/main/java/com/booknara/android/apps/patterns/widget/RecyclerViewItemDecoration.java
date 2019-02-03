package com.booknara.android.apps.patterns.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "RecyclerViewItemDecoration";

    private boolean mHideLastItemDivider = false;
    private boolean mHideFirstItemDivider = false;

    public RecyclerViewItemDecoration(@NonNull Context context, int orientation) {
        if (orientation != RecyclerView.VERTICAL && orientation != RecyclerView.HORIZONTAL) {
            throw new IllegalArgumentException("You have to pass a valid orientation " +
                    "using in RecyclerView. You passed a value of " + orientation);
        }

        int[] attrs = { android.R.attr.listDivider };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        ta.recycle();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        int itemCount = state.getItemCount();
        int position = parent.getChildAdapterPosition(view);

        if (hideDividerDrawable(itemCount, position)) {
            return;
        }

        super.getItemOffsets(outRect, view, parent, state);
    }

    public void setHideFirstItemDivider(boolean hideFirstItemDivider) {
        this.mHideFirstItemDivider = hideFirstItemDivider;
    }

    public void setHideLastItemDivider(boolean hideLastItemDivider) {
        this.mHideLastItemDivider = hideLastItemDivider;
    }

    private boolean hideDividerDrawable(int childCount, int currentPosition) {
        if (mHideLastItemDivider && currentPosition == childCount - 1) {
            Log.d(TAG, "hide last divider");
            return true;
        }

        if (mHideFirstItemDivider && currentPosition == 0) {
            Log.d(TAG, "hide first divider");
            return true;
        }

        return false;
    }
}
