package com.creactivestudio.lerntagebuchapp.evaluate_your_self;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.creactivestudio.lerntagebuchapp.R;
import java.util.HashMap;
import java.util.List;

public class EvaluateYourSelfRvAdapter extends RecyclerView.Adapter<EvaluateYourSelfRvAdapter.EvaluateViewHolder> {

    private Context context;
    Activity activity;
    private List<String> themeList;

    /**
     * Constructor bekommt diese Parametern
     * @param activity
     * @param context
     * @param themeList
     */
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


        for(int i=0; i<themeList.size();i++) //Setze alle gespeicherte Werte aus Shared Preferences ein.
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
            }
        }

        holder.tvThemeTitle.setText(String.valueOf(themeList.get(position)));
        holder.radioGroupEvaluation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // Wenn der Benutzer Radio Buttons drückt setze den Wert ein zu Shared Preferences
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId()==R.id.radioButtonRed)
                {
                    editor.putString(themeList.get(holder.getAdapterPosition()),"red"); // Aktualiesiere Sharedpref.
                    editor.apply();// Speicher die Änderungen
                }
                else if (radioGroup.getCheckedRadioButtonId()==R.id.radioButtonYellow)
                {
                    editor.putString(themeList.get(holder.getAdapterPosition()),"yellow");// Aktualiesiere Sharedpref.
                    editor.apply();// Speicher die Änderungen
                }
                else if(radioGroup.getCheckedRadioButtonId()==R.id.radioButtonGreen)
                {
                    editor.putString(themeList.get(holder.getAdapterPosition()),"green");// Aktualiesiere Sharedpref.
                    editor.apply();// Speicher die Änderungen
                }
            }
        });
     }

    /**
     *
     * Wir definieren wie viele Objekt in Recycler View sein muss. Weil wir die Daten aus der Array bekommen, geben wir lenght der Array ein.
     * @return size
     */
    @Override
    public int getItemCount() {
        return themeList.size();
    }

    /**
     * Initialisieren wir unser Views dass wir im Recycler View verwenden wollen.
     */
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
