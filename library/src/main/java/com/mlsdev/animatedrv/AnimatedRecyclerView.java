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
    private int orientation = LinearLayoutManager.VERTICAL;
    private boolean reverse = false;
    private int animationDuration = 600;
    private int layoutManagerType = LayoutManagerType.LINEAR;
    private int columns = 1;
    @AnimRes
    private int animation = R.anim.layout_animation_from_bottom;
    private LayoutAnimationController animationController;

    public AnimatedRecyclerView(Context context, int orientation, boolean reverse,
                                int animationDuration, int layoutManagerType, int columns,
                                int animation, LayoutAnimationController animationController) {
        super(context);
        this.orientation = orientation;
        this.reverse = reverse;
        this.animationDuration = animationDuration;
        this.layoutManagerType = layoutManagerType;
        this.columns = columns;
        this.animation = animation;
        this.animationController = animationController;
        init(context, null);
    }

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

        orientation = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerOrientation, orientation);
        reverse = typedArray.getBoolean(R.styleable.AnimatedRecyclerView_layoutManagerReverse, reverse);
        animationDuration = typedArray.getInt(R.styleable.AnimatedRecyclerView_animationDuration, animationDuration);
        layoutManagerType = typedArray.getInt(R.styleable.AnimatedRecyclerView_layoutManagerType, layoutManagerType);
        columns = typedArray.getInt(R.styleable.AnimatedRecyclerView_gridLayoutManagerColumns, columns);
        animation = typedArray.getResourceId(R.styleable.AnimatedRecyclerView_layoutAnimation, -1);

        if (animationController == null)
            animationController = animation != -1
                    ? AnimationUtils.loadLayoutAnimation(getContext(), animation)
                    : AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_from_bottom);

        animationController.getAnimation().setDuration(animationDuration);
        setLayoutAnimation(animationController);

        if (layoutManagerType == LayoutManagerType.LINEAR)
            setLayoutManager(new LinearLayoutManager(context, orientation, reverse));
        else if (layoutManagerType == LayoutManagerType.GRID)
            setLayoutManager(new GridLayoutManager(context, columns, orientation, reverse));
    }

    public static class Builder {
        private int orientation = LinearLayoutManager.VERTICAL;
        private boolean reverse = false;
        private int animationDuration = 600;
        private int layoutManagerType = LayoutManagerType.LINEAR;
        private int columns = 1;
        @AnimRes
        private int animation = R.anim.layout_animation_from_bottom;
        private LayoutAnimationController animationController;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder orientation(int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder reverse(boolean reverse) {
            this.reverse = reverse;
            return this;
        }

        public Builder animationDuration(int animationDuration) {
            this.animationDuration = animationDuration;
            return this;
        }

        public Builder layoutManagerType(@LayoutManagerType int layoutManagerType) {
            this.layoutManagerType = layoutManagerType;
            return this;
        }

        public Builder columns(int columns) {
            this.columns = columns;
            return this;
        }

        public Builder animation(@AnimRes int animation) {
            this.animation = animation;
            return this;
        }

        public Builder aimationController(LayoutAnimationController animationController) {
            this.animationController = animationController;
            return this;
        }

        public AnimatedRecyclerView build() {
            return new AnimatedRecyclerView(
                    context, orientation, reverse, animationDuration, layoutManagerType, columns,
                    animation, animationController
            );
        }

    }

    public void notifyDataSetChanged() {
        getAdapter().notifyDataSetChanged();
        scheduleLayoutAnimation();
    }

    public @interface LayoutManagerType {
        int LINEAR = 0;
        int GRID = 1;
    }
}
