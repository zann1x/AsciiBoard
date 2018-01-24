package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public CategoryHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.recycler_category_item);
    }

}
