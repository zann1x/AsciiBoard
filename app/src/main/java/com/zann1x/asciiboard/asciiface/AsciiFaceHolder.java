package com.zann1x.asciiboard.asciiface;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zann1x.asciiboard.R;

public class AsciiFaceHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public AsciiFaceHolder(View view) {
        super(view);
        textView = view.findViewById(R.id.recycler_item);
    }

}
