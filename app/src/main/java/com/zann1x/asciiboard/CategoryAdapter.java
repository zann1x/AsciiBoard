package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

    private List<AsciiFaceCategory> categories;

    public CategoryAdapter(AsciiFaceCategory[] data) {
        categories = Arrays.asList(data);
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        holder.textView.setText(categories.get(position).toString());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO switch category tab
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

}
