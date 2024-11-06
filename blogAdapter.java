package com.example.newsy;

// BlogAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder> {
    private List<blog> blogList;

    public BlogAdapter(List<blog> blogList) {
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        blog blog = blogList.get(position);
        holder.titleTextView.setText(blog.getTitle());
        holder.contentTextView.setText(blog.getContent());
        holder.authorTextView.setText("By: " + blog.getAuthor());
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, authorTextView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.blogTitle);
            contentTextView = itemView.findViewById(R.id.blogContent);
            authorTextView = itemView.findViewById(R.id.blogAuthor);
        }
    }
}
