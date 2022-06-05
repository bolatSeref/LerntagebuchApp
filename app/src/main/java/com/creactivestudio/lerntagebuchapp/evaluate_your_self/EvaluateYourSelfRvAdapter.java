package com.creactivestudio.lerntagebuchapp.evaluate_your_self;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creactivestudio.lerntagebuchapp.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EvaluateYourSelfRvAdapter extends RecyclerView.Adapter<EvaluateYourSelfRvAdapter.EvaluateViewHolder> {

    private Context context;
    Activity activity;
    private List<String> themeList;
    final String radioGroupMapKey="radio_group_map_key";

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
        HashMap<String, String> selectedRadioMap=new HashMap<>();
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();


        /**
         * Set radio buttons with correct values from shared pref.
         */
        for(int i=0; i<themeList.size();i++)
        {
            if(position==i)
            {
                String prefString=pref.getString(themeList.get(i),"null");
                if(prefString.equals("red"))
                {
                    holder.radioButtonRed.setChecked(true);
                }
                else if(prefString.equals("yellow"))
                {
                    holder.radioButtonYellow.setChecked(true);
                }
                else if(prefString.equals("green"))
                {
                    holder.radioButtonGreen.setChecked(true);
                }
                // holder.radioButtonRed.setBackgroundColor(context.getColor(R.color.design_default_color_error));
            }
        }

        holder.tvThemeTitle.setText(String.valueOf(themeList.get(position)));
        holder.radioGroupEvaluation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(context,String.valueOf(i), Toast.LENGTH_SHORT).show();
                if(radioGroup.getCheckedRadioButtonId()==R.id.radioButtonRed)
                //if( radioGroup.getTag().equals("red"))
                {
                    Toast.makeText(context,String.valueOf("red"), Toast.LENGTH_SHORT).show();
                   // selectedRadioMap.put(themeList.get(holder.getAdapterPosition()).toString(),"red");
                  //  saveMap(selectedRadioMap);

                    editor.putString(themeList.get(holder.getAdapterPosition()),"red");
                    editor.commit();
                 //   editor.apply();
                }
                else if (radioGroup.getCheckedRadioButtonId()==R.id.radioButtonYellow)
                {
                    Toast.makeText(context,String.valueOf("yellow"), Toast.LENGTH_SHORT).show();
                    //selectedRadioMap.put(themeList.get(holder.getAdapterPosition()).toString(),"yellow");
                    editor.putString(themeList.get(holder.getAdapterPosition()),"yellow");
                    editor.commit();
                }
                else if(radioGroup.getCheckedRadioButtonId()==R.id.radioButtonGreen)
                {
                    Toast.makeText(context,String.valueOf("green"), Toast.LENGTH_SHORT).show();
                    //selectedRadioMap.put(themeList.get(holder.getAdapterPosition()).toString(),"green");
                    editor.putString(themeList.get(holder.getAdapterPosition()),"green");
                    editor.commit();
                }


            }
        });


     }

     private void saveMap(Map<String, String> inputMap)
     {
         SharedPreferences sharedPreferences=context.getApplicationContext().getSharedPreferences("myChoices", Context.MODE_PRIVATE);
         if(sharedPreferences!=null)
         {
             JSONObject jsonObject=new JSONObject(inputMap);
             String jsonString=jsonObject.toString();
             SharedPreferences.Editor editor=sharedPreferences.edit();
           //  editor.remove(radioGroupMapKey).apply();
             editor.putString(radioGroupMapKey, jsonString);
             editor.commit();
         }
     }

    private Map<String, String> loadMap() {
        Map<String, String> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = context.getApplicationContext().getSharedPreferences("myChoices",
                Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString(radioGroupMapKey, (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    outputMap.put(key, (String) jsonObject.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputMap;
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
