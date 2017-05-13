package com.jermaine.awesomelibraries.view.adapter.repolist.viewholder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jermaine.awesomelibraries.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class RepoViewHolder extends RecyclerView.ViewHolder {
    @BindViews({R.id.id, R.id.name, R.id.description})
    public List<TextView> mTextViews;

    @BindView(R.id.cardView)
    public CardView mCardView;

    public RepoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
