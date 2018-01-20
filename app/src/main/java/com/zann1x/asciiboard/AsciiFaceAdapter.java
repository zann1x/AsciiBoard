package com.zann1x.asciiboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AsciiFaceAdapter extends RecyclerView.Adapter<AsciiFaceHolder> {

    MainInputService mainInputService;
    private List<String> asciiFaces;

    public AsciiFaceAdapter(MainInputService mis, List<String> data) {
        this.mainInputService = mis;
        this.asciiFaces = data;
    }

    @Override
    public AsciiFaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem, parent, false);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextSize(30.f);
        textView.setVerticalScrollBarEnabled(true);
        return new AsciiFaceHolder(textView);
    }

    @Override
    public void onBindViewHolder(AsciiFaceHolder holder, final int position) {
        holder.textView.setText(asciiFaces.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainInputService.onKey(position, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asciiFaces.size();
    }

}
