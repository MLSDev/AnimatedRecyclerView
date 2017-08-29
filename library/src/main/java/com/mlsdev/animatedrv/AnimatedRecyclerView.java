package com.mlsdev.animatedrv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

public class AnimatedRecyclerView extends RecyclerView {

    public AnimatedRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @SuppressLint("Recycle")
    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimatedRecyclerView, 0, 0);

        int layoutManagerType = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerType, LayoutManagerType.LINEAR);

        if (layoutManagerType == LayoutManagerType.LINEAR) {
            int orientation = typedArray.getInt(R.styleable.AnimatedRecyclerView_linearLayoutManagerOrientation, LinearLayoutManager.VERTICAL);
            boolean reverse = typedArray.getBoolean(R.styleable.AnimatedRecyclerView_linearLayoutManagerReverse, false);
            int animationDuration = typedArray.getInt(R.styleable.AnimatedRecyclerView_animationDuration, 400);
            setLayoutManager(new LinearLayoutManager(context, orientation, reverse));
            LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_from_bottom);
            animationController.getAnimation().setDuration(animationDuration);
            setLayoutAnimation(animationController);
        }

    }

    public void notifyDataSetChanged() {
        getAdapter().notifyDataSetChanged();
        scheduleLayoutAnimation();
    }

    @interface LayoutManagerType {
        int LINEAR = 0;
        int GRID = 1;
    }
}
