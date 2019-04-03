package com.example.user.mvvm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.mvvm.Model.Post;
import com.example.user.mvvm.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    List<Post> posts;
    Context context;

    public PostAdapter(List<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view=  LayoutInflater.from(context).inflate(R.layout.inflate,viewGroup,false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post=posts.get(i);
        viewHolder.body.setText(post.getBody());
        viewHolder.title.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView body,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            body=itemView.findViewById(R.id.body);
            title=itemView.findViewById(R.id.title);
        }
    }
}
