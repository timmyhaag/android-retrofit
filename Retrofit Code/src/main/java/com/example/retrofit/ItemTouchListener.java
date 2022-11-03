package com.example.retrofit;

import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class ItemTouchListener implements RecyclerView.OnItemTouchListener {
    GestureDetector gestureDetector;
    private RecyclerViewItemClickListener clickListener;

    public ItemTouchListener(Context context, final RecyclerView recyclerView, final RecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        View child = recyclerView.findChildViewUnder(event.getX(), event.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(event)) {
            clickListener.onClick(child, recyclerView.getChildLayoutPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowInterceptTouchEvent) {

    }
}