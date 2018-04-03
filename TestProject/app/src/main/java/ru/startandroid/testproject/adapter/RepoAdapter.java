package ru.startandroid.testproject.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.startandroid.testproject.R;
import ru.startandroid.testproject.activity.MainActivity;
import ru.startandroid.testproject.api.UserRepo;

/**
 * Created by Виктория on 02.03.2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder>{

    private List<UserRepo> repos;

    public RepoAdapter(List<UserRepo> repos) {
        this.repos = repos;
    }

    public void setRepos(List<UserRepo> repos) {
        this.repos = repos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserRepo repo = repos.get(position);
        holder.name.setText(repo.getName());
        holder.author.setText(repo.getOwner().getLogin());

        Picasso.with(holder.context)
                .load(repo.getOwner().getAvatarURL())
                .resize(150, 150)
                .placeholder(R.drawable.repo_item)
                .error(R.drawable.repo_item)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (repos == null)
            return 0;
        return repos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView author;
        ImageView imageView;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_name);
            author = (TextView) itemView.findViewById(R.id.item_author);
            imageView = (ImageView) itemView.findViewById(R.id.repo_image);
            context = itemView.getContext();
        }
    }
}
