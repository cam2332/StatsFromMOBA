package com.example.statsfrommoba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

public class PlayerInfoSearchResultListAdapter extends BaseAdapter implements Filterable {

    ArrayList<StringPair> mData = new ArrayList();
    ArrayList<StringPair> mDataFilerList = new ArrayList();
    ValueFilter valueFilter;
    private LayoutInflater mInflater;

    public PlayerInfoSearchResultListAdapter(ArrayList<StringPair> items) {
        mData = items;
        mDataFilerList = items;
    }

    public void addItem(final String key, final String value) {
        mData.add(new StringPair(key,value));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(mInflater == null) {
            mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        //RowItemBinding rowItemBinding = DataBindingUtil.inflate(inflater, R.layout.row_item, parent, false);
        //rowItemBinding.stringName.setText(mData.get(position));

        PlayerInfoSearchResult holder = null;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);
            holder = new PlayerInfoSearchResult();
            holder.playerName = (TextView) convertView.findViewById(R.id.textView_player_name);
            holder.playerRank = (TextView) convertView.findViewById(R.id.textView_player_rank);
            convertView.setTag(holder);
        } else {
            holder = (PlayerInfoSearchResult) convertView.getTag();
        }
        holder.playerName.setText(mData.get(position).key);
        holder.playerRank.setText(mData.get(position).value);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length() > 0) {
                List<StringPair> filterList = new ArrayList<>();
                for(int i = 0; i < mDataFilerList.size(); i++) {
                    if((mDataFilerList.get(i).key.toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mDataFilerList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mDataFilerList.size();
                results.values = mDataFilerList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData = (ArrayList<StringPair>) results.values;
            notifyDataSetChanged();
        }
    }
}
