package com.creactivestudio.lerntagebuchapp.evaluate_your_self;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.R;

import java.util.ArrayList;
import java.util.List;

public class EvaluateYourSelfRvAdapter extends RecyclerView.Adapter<EvaluateYourSelfRvAdapter.EvaluateViewHolder> {

    private Context context;
    Activity activity;
    private List<String> themeList;

    public EvaluateYourSelfRvAdapter (Activity activity, Context context, List<String> themeList)
    {
        this.activity=activity;
        this.context=context;
        this.themeList=themeList;
    }

    @NonNull
    @Override
    public EvaluateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.rv_evaluate_your_self_item_row, parent, false);
        return new EvaluateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluateViewHolder holder, int position) {
        holder.tvThemeTitle.setText(String.valueOf(themeList.get(position)));
        holder.radioGroupEvaluation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(context,String.valueOf(i), Toast.LENGTH_SHORT).show();

            }
        });

        holder.radioGroupEvaluation.getCheckedRadioButtonId();

    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public class EvaluateViewHolder extends RecyclerView.ViewHolder {

        TextView tvThemeTitle;
        RadioButton radioButtonRed, radioButtonYellow, radioButtonGreen;
        RadioGroup radioGroupEvaluation;

        public EvaluateViewHolder(@NonNull View itemView) {
            super(itemView);

            tvThemeTitle=itemView.findViewById(R.id.tvThemeTitle);
            radioButtonGreen=itemView.findViewById(R.id.radioButtonGreen);
            radioButtonYellow=itemView.findViewById(R.id.radioButtonYellow);
            radioButtonRed=itemView.findViewById(R.id.radioButtonRed);
            radioGroupEvaluation=itemView.findViewById(R.id.radioGroup);


        }
    }

}
