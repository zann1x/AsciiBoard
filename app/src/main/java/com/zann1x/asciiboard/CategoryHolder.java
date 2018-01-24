package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public Button button;

    public CategoryHolder(Button button) {
        super(button);
        this.button = button.findViewById(R.id.recycler_category_button);
    }

}
