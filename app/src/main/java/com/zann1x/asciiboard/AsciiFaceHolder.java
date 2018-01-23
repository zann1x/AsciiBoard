package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

public class AsciiFaceHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public AsciiFaceHolder(TextView textView) {
        super(textView);
        this.textView = textView.findViewById(R.id.recycler_item);
    }

}
