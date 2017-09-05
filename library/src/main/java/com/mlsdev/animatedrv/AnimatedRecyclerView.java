package com.mlsdev.animatedrv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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

        int orientation = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerOrientation, LinearLayoutManager.VERTICAL);
        boolean reverse = typedArray.getBoolean(R.styleable.AnimatedRecyclerView_layoutManagerReverse, false);
        int animationDuration = typedArray.getInt(R.styleable.AnimatedRecyclerView_animationDuration, 500);
        int layoutManagerType = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerType, LayoutManagerType.LINEAR);
        int columns = typedArray.getInt(R.styleable.AnimatedRecyclerView_gridLayoutManagerColumns, 1);
        @AnimRes int animation = typedArray.getResourceId(R.styleable.AnimatedRecyclerView_layoutAnimation, -1);
        LayoutAnimationController animationController = animation != -1
                ? AnimationUtils.loadLayoutAnimation(getContext(), animation)
                : AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_from_bottom);
        animationController.getAnimation().setDuration(animationDuration);
        setLayoutAnimation(animationController);

        if (layoutManagerType == LayoutManagerType.LINEAR)
            setLayoutManager(new LinearLayoutManager(context, orientation, reverse));
        else if (layoutManagerType == LayoutManagerType.GRID)
            setLayoutManager(new GridLayoutManager(context, columns, orientation, reverse));
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
