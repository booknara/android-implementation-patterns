package com.booknara.android.apps.patterns.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.booknara.android.apps.patterns.R;

public class CustomFontTextView extends AppCompatTextView {
    private static final String TAG = "CustomFontTextView";

    public CustomFontTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomFontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
        String fontName = a.getString(R.styleable.CustomFontTextView_typeface);
        try {
            if (fontName != null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "font/" + fontName);
                setTypeface(myTypeface);
            }
        } catch (Exception e) {
            Log.e(TAG, "failed to load custom font : " + e.getMessage());
        }
        a.recycle();
    }
}
