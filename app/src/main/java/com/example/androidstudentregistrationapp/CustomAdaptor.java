package com.example.androidstudentregistrationapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private Context context;
    private ArrayList<Student> student;

    CustomAdaptor(Context context, ArrayList student) {
        this.context = context;
        this.student = student;
    }

    @NonNull
    @Override
    public CustomAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.student_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdaptor.MyViewHolder holder, int position) {
        int position2 = holder.getAdapterPosition();
        holder.id.setText(student.get(position).getId());
        holder.name.setText(student.get(position).getName());
        holder.programme.setText(student.get(position).getProgramme());
        holder.gender.setText(student.get(position).getGender());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentUpdate.class);
                intent.putExtra("id", student.get(position2).getId());
                intent.putExtra("name", student.get(position2).getName());
                intent.putExtra("programme", student.get(position2).getProgramme());
                intent.putExtra("gender", student.get(position2).getGender());
                intent.putExtra("cellNo", student.get(position2).getCellNo());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name, programme, gender;
        private CardView layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            programme = itemView.findViewById(R.id.programmeview);
            gender = itemView.findViewById(R.id.gender);
            layout = itemView.findViewById(R.id.cardView);
        }
    }
}
