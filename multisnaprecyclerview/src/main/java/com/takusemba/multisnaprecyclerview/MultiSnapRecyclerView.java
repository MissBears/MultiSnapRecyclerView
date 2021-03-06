package com.takusemba.multisnaprecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * MultiSnapRecyclerView
 * Note that only LinearLayoutManger is supported, and reverse layout is not supported.
 *
 * @author takusemba
 * @since 30/07/2017
 **/
public class MultiSnapRecyclerView extends RecyclerView {

    private MultiSnapHelper multiSnapHelper;

    public MultiSnapRecyclerView(Context context) {
        this(context, null);
    }

    public MultiSnapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiSnapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MultiSnapRecyclerView);
        SnapGravity gravity = SnapGravity.valueOf(a.getInt(R.styleable.MultiSnapRecyclerView_msrv_gravity, 0));
        int snapCount = a.getInteger(R.styleable.MultiSnapRecyclerView_msrv_snap_count, 1);
        a.recycle();
        multiSnapHelper = new MultiSnapHelper(gravity, snapCount);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnFlingListener(null);
        multiSnapHelper.attachToRecyclerView(MultiSnapRecyclerView.this);
    }

    /**
     * sets Snap Listener to RecyclerView
     *
     * @param listener OnSnapListener of MultiSnapRecyclerView
     */
    public void setOnSnapListener(@NonNull final OnSnapListener listener) {
        multiSnapHelper.setListener(listener);
    }
}
