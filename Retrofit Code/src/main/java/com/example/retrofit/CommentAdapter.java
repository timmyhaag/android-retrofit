package com.example.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CustomViewHolder> {
    List<Comments> list;
    Context context;

    public CommentAdapter(List<Comments> list, Context context) {
        this.list = list;
        this.context = context;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        TextView postTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            postTitle = view.findViewById(R.id.title);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycle_row, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {
        viewHolder.postTitle.setText(list.get(position).getEmail() + "\nClick here to view comments");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String getItemDesc(int i) {
        return list.get(i).getPost();
    }
}