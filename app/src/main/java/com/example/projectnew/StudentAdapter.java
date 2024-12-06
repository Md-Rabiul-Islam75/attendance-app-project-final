package com.example.projectnew;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class   StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    ArrayList<StudentItem> studentItems;
    Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public StudentAdapter(Context context, ArrayList<StudentItem> studentItems) {
        this.studentItems = studentItems;
        this.context = context;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView roll;
        TextView name;
        TextView status;
        CardView cardView;

        public StudentViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            roll = itemView.findViewById(R.id.roll);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            cardView=itemView.findViewById(R.id.cardView);

            // Only set click listener if one is provided
            if (onItemClickListener != null) {
                itemView.setOnClickListener(v -> onItemClickListener.onClick(getAdapterPosition()));
                itemView.setOnCreateContextMenuListener(this);
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(getAdapterPosition(), 0, 0, "Edit");
            menu.add(getAdapterPosition(), 1, 0, "Delete");
        }
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.roll.setText(studentItems.get(position).getRoll()+"");
        holder.name.setText(studentItems.get(position).getName());
        holder.status.setText(studentItems.get(position).getStatus());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }

    public int getColor(int position) {
        String status = studentItems.get(position).getStatus();
        if (status.equals("P")) {
            return context.getResources().getColor(R.color.green);
        } else if (status.equals("A")) {
            return context.getResources().getColor(R.color.red);
        } else {
            return context.getResources().getColor(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }
}