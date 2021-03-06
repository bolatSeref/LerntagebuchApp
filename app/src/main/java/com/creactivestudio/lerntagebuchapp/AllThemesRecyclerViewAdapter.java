package com.creactivestudio.lerntagebuchapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllThemesRecyclerViewAdapter extends RecyclerView.Adapter<AllThemesRecyclerViewAdapter.AllThemesViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList goalId, goalTheme, goalTime;

    public AllThemesRecyclerViewAdapter (Activity activity, Context context, ArrayList goalIdList, ArrayList goalThemeList, ArrayList goalTimeList )
    {
        this.activity=activity;
        this.context=context;
        this.goalId =goalIdList;
        this.goalTheme =goalThemeList;
        this.goalTime =goalTimeList;
    }


    @NonNull
    @Override
    public AllThemesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.rv_all_themes_item_row, parent, false);
        return new AllThemesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllThemesViewHolder holder, int position) {
        holder.tvGoalThemeAllThemes.setText(String.valueOf(goalTheme.get(position)));
        holder.tvGoalTimeAllThemes.setText(String.valueOf(goalTime.get(position) + " " + context.getString(R.string.minute)));

        holder.imgStartTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, StatusActivity.class);
                intent.putExtra("goalTime", String.valueOf(goalTime.get(position)));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return goalId.size();
    }

    public class AllThemesViewHolder extends RecyclerView.ViewHolder {
        TextView tvGoalThemeAllThemes, tvGoalTimeAllThemes;
        ImageView imgStartTheme;

        public AllThemesViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGoalThemeAllThemes=itemView.findViewById(R.id.tvGoalThemeAllThemes);
            tvGoalTimeAllThemes=itemView.findViewById(R.id.tvGoalTimeAllThemes);
            imgStartTheme=itemView.findViewById(R.id.imgStartTheme);


        }
    }

}
