package com.example.mobile_app;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RevistaAdapter extends RecyclerView.Adapter<RevistaAdapter.RevistaViewHolder> {
    private Context context;
    private ArrayList<Revista> revistaList;
    private OnRevistaClickListener listener;

    public interface OnRevistaClickListener {
        void onRevistaClick(Revista revista);
    }

    public RevistaAdapter(Context context, ArrayList<Revista> revistaList, OnRevistaClickListener listener) {
        this.context = context;
        this.revistaList = revistaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RevistaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_revista, parent, false);
        return new RevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RevistaViewHolder holder, int position) {
        Revista revista = revistaList.get(position);

        // Cargar el ícono de la revista
        Glide.with(context)
                .load(revista.getPortada())
                .placeholder(R.drawable.revista_placeholder)
                .error(R.drawable.revista_placeholder)
                .into(holder.icon);

        // Establecer el título y el resumen
        holder.titulo.setText(revista.getName() != null ? revista.getName() : "Sin título");
        if (revista.getDescription() != null) {
            String description = Html.fromHtml(revista.getDescription(), Html.FROM_HTML_MODE_LEGACY).toString();
            holder.resumen.setText(description);
        } else {
            holder.resumen.setText("Sin descripción");
        }

        // Añadir animación
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));

        // Configurar el click listener
        holder.itemView.setOnClickListener(v -> listener.onRevistaClick(revista));
    }

    @Override
    public int getItemCount() {
        return revistaList.size();
    }

    public static class RevistaViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView titulo;
        TextView resumen;

        public RevistaViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.revistaIcon);
            titulo = itemView.findViewById(R.id.revistaTitulo);
            resumen = itemView.findViewById(R.id.revistaResumen);
        }
    }
}