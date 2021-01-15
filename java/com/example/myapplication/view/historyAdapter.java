package com.example.myapplication.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.HistoryEntry;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.HistoryItemViewHolder>{

    private ArrayList<HistoryEntry> history;
    historyAdapter(){
        history = new ArrayList<>();
    }

    void initialize(ArrayList<HistoryEntry> history){
        this.history = history;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_item, viewGroup, false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, int i) {
        historyItemViewHolder.bind(history.get(i));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    class HistoryItemViewHolder extends RecyclerView.ViewHolder {

        private TextView historyText;
        private Button historyButton;

        HistoryItemViewHolder(View itemView) {
            super(itemView);
            historyText = itemView.findViewById(R.id.history_text);
            historyButton = itemView.findViewById(R.id.history_button);
        }

        void bind(HistoryEntry historyItem) {
            historyText.setText(historyItem.getTextRepresentation());
            historyButton.setOnClickListener(v -> {
                Toast.makeText(historyButton.getContext(),
                        historyItem.getTextRepresentation(),
                        Toast.LENGTH_SHORT)
                        .show();
                historyText.setVisibility(View.GONE);
            });
        }
    }
}