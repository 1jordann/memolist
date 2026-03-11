package com.example.memolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    private ArrayList<Memo> memoList;

    public MemoAdapter(ArrayList<Memo> memoList) {
        this.memoList = memoList;
    }

    public static class MemoViewHolder extends RecyclerView.ViewHolder {

        public TextView memoTitle;
        public TextView memoDescription;
        public TextView memoPriority;
        public TextView memoDate;

        public MemoViewHolder(View itemView) {
            super(itemView);

            memoTitle = itemView.findViewById(R.id.memoTitle);
            memoDescription = itemView.findViewById(R.id.memoDescription);
            memoPriority = itemView.findViewById(R.id.memoPriority);
            memoDate = itemView.findViewById(R.id.memoDate);
        }
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_item, parent, false);

        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {

        Memo memo = memoList.get(position);

        holder.memoTitle.setText(memo.getTitle());
        holder.memoDescription.setText(memo.getDescription());
        holder.memoPriority.setText(memo.getPriority());
        holder.memoDate.setText(memo.getDate());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }
}