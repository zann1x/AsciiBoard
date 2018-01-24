package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AsciiFaceHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public AsciiFaceHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.recycler_item);
    }

}
