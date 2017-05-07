package com.example.bmoriyama.rxincrementalsearch.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.bmoriyama.rxincrementalsearch.model.Item;
import com.example.bmoriyama.rxincrementalsearch.ui.viewholder.SearchResultViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsListAdapter extends ArrayAdapter {

    private Context context;
    private int resourceId;
    private SearchResultViewHolder viewHolder;
    List<Item> itemList;

    public SearchResultsListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
        this.resourceId = resource;
        itemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resourceId, parent, false);
            viewHolder = new SearchResultViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SearchResultViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(itemList.get(position).getTitle());
        viewHolder.tvAnswerCount.setText(itemList.get(position).getAnswerCount().toString());
        viewHolder.tvOwnerDisplayName.setText(itemList.get(position).getOwner().getDisplayName());
        return convertView;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
