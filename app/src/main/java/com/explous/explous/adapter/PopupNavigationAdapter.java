package com.explous.explous.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.explous.explous.R;
import com.explous.explous.Value;

/**
 * Created by Administrator on 2016/10/26.
 */

public class PopupNavigationAdapter extends RecyclerView.Adapter<PopupNavigationAdapter.ViewHolder> {
    private Context context;

    public PopupNavigationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_navigation_popup, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position >= Value.showTypes.size()) {
            holder.title.setText(Value.markPath.getName());
            return;
        }
        holder.title.setText("" + Value.showTypes.get(position));
    }

    @Override
    public int getItemCount() {
        //check wether need to add the markPath in the popupWindow
        if (Value.markPath == null)
            return Value.showTypes.size();
        return Value.showTypes.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
