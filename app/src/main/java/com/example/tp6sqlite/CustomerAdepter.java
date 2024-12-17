package com.example.tp6sqlite;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CustomerAdepter  extends RecyclerView.Adapter<CustomerAdepter.MyViewHolder>{

    private List<Offre> offresList ;
    private Context context ;

    public CustomerAdepter(Context  context , List<Offre> offresList ) {

        this.offresList = offresList;
        this.context = context ;
    }


    @NonNull
    @Override
    public CustomerAdepter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdepter.MyViewHolder holder, int position) {
        Offre o= offresList.get(position);
        holder.poste.setText(o.getPoste());
        holder.descrpition.setText(o.getDescription());
        holder.id.setText(String.valueOf(o.getId()));

        // Gestion du clic directement dans onBindViewHolder
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("description", o.getDescription());
            i.putExtra("poste", o.getPoste());
            i.putExtra("idoffre" , String.valueOf(o.getId()));
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return this.offresList.size();
    }

    // Nouvelle méthode pour mettre à jour la liste
    public void updateList(List<Offre> newList) {
        this.offresList = newList;
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, poste , descrpition;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.id_view);
            poste =itemView.findViewById(R.id.poste_view);
            descrpition = itemView.findViewById(R.id.description_view);



        }
    }
}
