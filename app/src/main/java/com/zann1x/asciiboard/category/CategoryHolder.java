package com.zann1x.asciiboard.category;

import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.zann1x.asciiboard.R;

public class CategoryHolder extends RecyclerView.ViewHolder {

    public Button button;

    public CategoryHolder(Button button) {
        super(button);
        this.button = button.findViewById(R.id.recycler_category_button);
    }

}
