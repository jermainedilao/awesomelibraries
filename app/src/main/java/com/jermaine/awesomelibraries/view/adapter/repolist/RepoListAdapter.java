package com.jermaine.awesomelibraries.view.adapter.repolist;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jermaine.awesomelibraries.R;
import com.jermaine.awesomelibraries.api.response.Repo;
import com.jermaine.awesomelibraries.view.adapter.repolist.callback.OnItemClickListener;
import com.jermaine.awesomelibraries.view.adapter.repolist.viewholder.RepoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RepoListAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> mRepoList;
    private OnItemClickListener mOnItemClickListener;

    public RepoListAdapter(List<Repo> repoList) {
        updateData(repoList);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Repo repo = mRepoList.get(position);

        // 0 = id
        holder.mTextViews.get(0).setText(repo.getId());
        // 1 = name
        holder.mTextViews.get(1).setText(repo.getName());
        // 2 = description
        holder.mTextViews.get(2).setText(repo.getDescription());

        final int positionCopy = position;
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(positionCopy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }

    public Repo get(int position) {
        return mRepoList.get(position);
    }

    public void updateData(List<Repo> repoList) {
        if (mRepoList == null) {
            mRepoList = new ArrayList<>();
        } else {
            mRepoList.clear();
        }
        mRepoList.addAll(repoList);
        notifyDataSetChanged();
    }
}
