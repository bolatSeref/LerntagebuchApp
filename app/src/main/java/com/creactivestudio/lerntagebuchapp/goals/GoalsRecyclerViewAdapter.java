package com.creactivestudio.lerntagebuchapp.goals;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;

public class GoalsRecyclerViewAdapter extends RecyclerView.Adapter<GoalsRecyclerViewAdapter.GoalsViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList goalId, goalTheme, goalTime;

    public GoalsRecyclerViewAdapter(Activity activity, Context context, ArrayList goalIdList, ArrayList goalThemeList, ArrayList goalTimeList)
    {
        this.activity=activity;
        this.context=context;
        this.goalId =goalIdList;
        this.goalTheme =goalThemeList;
        this.goalTime =goalTimeList;

    }


    @NonNull
    @Override
    public GoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Bestimmen welche layout für jeder row verwendet wird
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.goal_rv_item_layout, parent, false);
        return new GoalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsViewHolder holder, int position) {
        holder.tvGoalId.setText(String.valueOf(goalId.get(position)));
        holder.tvGoalTheme.setText(String.valueOf(goalTheme.get(position)));
        holder.tvGoalTime.setText(String.valueOf(goalTime.get(position) + " " + context.getString(R.string.minute)));

        holder.imgUpdateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 12.05.22 noch nicht fertig
                Toast.makeText(context, "Update Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDeleteGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 12.05.22 noch nicht fertig
                Toast.makeText(context, "Delete Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Beschreibt Anzahl der Zeilen in Recycler View. Weil vorher können wir nicht
     * wissen wie viele Elemente gibt, geben wir als Rückgabewert length von ArrayList.
     * @return
     */
    @Override
    public int getItemCount() {
        return goalId.size();
    }

    public class GoalsViewHolder extends RecyclerView.ViewHolder {

        TextView tvGoalId, tvGoalTheme, tvGoalTime;
        ImageView imgUpdateGoal, imgDeleteGoal;

        public GoalsViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialition aller Views, die in RecyclerView verwendet wird.
            tvGoalId =itemView.findViewById(R.id.tvGoalId);
            tvGoalTheme =itemView.findViewById(R.id.tvGoalTheme);
            tvGoalTime =itemView.findViewById(R.id.tvGoalTime);

            imgDeleteGoal =itemView.findViewById(R.id.imgDeleteGoal);
            imgUpdateGoal =itemView.findViewById(R.id.imgUpdateGoal);

        }
    }
}
