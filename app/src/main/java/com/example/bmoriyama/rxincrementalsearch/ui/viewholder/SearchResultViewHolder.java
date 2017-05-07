package com.example.bmoriyama.rxincrementalsearch.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.bmoriyama.rxincrementalsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultViewHolder {

    @BindView(R.id.tv_title)
    public TextView tvTitle;

    @BindView(R.id.tv_answer_count)
    public TextView tvAnswerCount;

    @BindView(R.id.tv_owner_display_name)
    public TextView tvOwnerDisplayName;

    public SearchResultViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
