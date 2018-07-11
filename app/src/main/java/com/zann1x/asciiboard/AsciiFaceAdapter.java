package com.zann1x.asciiboard;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AsciiFaceAdapter extends RecyclerView.Adapter<AsciiFaceHolder> {

    private MainInputService mainInputService;
    private List<String> asciiFaces;

    public AsciiFaceAdapter(MainInputService mainInputService, List<String> data) {
        this.mainInputService = mainInputService;
        asciiFaces = data;
    }

    @Override
    public AsciiFaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem, parent, false);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(32.f);
        textView.setTextColor(Color.BLACK);
        textView.setVerticalScrollBarEnabled(true);

        return new AsciiFaceHolder(textView);
    }

    @Override
    public void onBindViewHolder(AsciiFaceHolder holder, final int position) {
        final int pos = holder.getAdapterPosition();
        holder.textView.setText(asciiFaces.get(pos));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainInputService.onKey(pos, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asciiFaces.size();
    }

}
