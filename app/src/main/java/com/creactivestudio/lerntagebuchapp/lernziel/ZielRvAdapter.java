package com.creactivestudio.lerntagebuchapp.lernziel;

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

public class ZielRvAdapter extends RecyclerView.Adapter<ZielRvAdapter.ZiewViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList zielId, zielThema, zielZeit;

    public ZielRvAdapter (Activity activity, Context context, ArrayList zielIdList, ArrayList zielThemaList, ArrayList zielZeitList)
    {
        this.activity=activity;
        this.context=context;
        this.zielId=zielIdList;
        this.zielThema=zielThemaList;
        this.zielZeit=zielZeitList;

    }


    @NonNull
    @Override
    public ZielRvAdapter.ZiewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.ziel_rv_item, parent, false);
        return new ZiewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZielRvAdapter.ZiewViewHolder holder, int position) {
        holder.tvZielId.setText(String.valueOf(zielId.get(position)));
        holder.tvZielThema.setText(String.valueOf(zielThema.get(position)));
        holder.tvZielZeit.setText(String.valueOf(zielZeit.get(position)));

        holder.imgUpdateZiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Update Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.imgDeleteZiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return zielId.size();
    }

    public class ZiewViewHolder extends RecyclerView.ViewHolder {

        TextView tvZielId, tvZielThema, tvZielZeit;
        ImageView imgUpdateZiel, imgDeleteZiel;

        public ZiewViewHolder(@NonNull View itemView) {
            super(itemView);

            tvZielId=itemView.findViewById(R.id.tvZielId);
            tvZielThema=itemView.findViewById(R.id.tvZielThema);
            tvZielZeit=itemView.findViewById(R.id.tvZielZeit);

            imgDeleteZiel=itemView.findViewById(R.id.imgDeleteZiel);
            imgUpdateZiel=itemView.findViewById(R.id.imgUpdateZiel);

        }
    }
}
