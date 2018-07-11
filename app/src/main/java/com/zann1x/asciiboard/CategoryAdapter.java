package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private MainInputService mainInputService;
    private List<Category> categories;

    public CategoryAdapter(MainInputService mainInputService, Set<Category> data) {
        this.mainInputService = mainInputService;
        categories = new ArrayList<>(data);
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = (Button) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercategorybutton, parent, false);
        return new CategoryHolder(button);
    }

    @Override
    public void onBindViewHolder(final CategoryHolder holder, final int position) {
        final Category category = categories.get(position);
        holder.button.setText(category.toString());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainInputService.switchCategory(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

}
