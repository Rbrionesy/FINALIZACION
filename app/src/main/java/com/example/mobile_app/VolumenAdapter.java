package com.example.mobile_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VolumenAdapter extends ArrayAdapter<Volumen> {

    public VolumenAdapter(Context context, ArrayList<Volumen> volumenes) {
        super(context, 0, volumenes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_volumen, parent, false);
        }

        Volumen volumen = getItem(position);
        if (volumen == null) {
            return convertView;
        }

        TextView title = convertView.findViewById(R.id.volumenTitle);
        title.setText("Volumen " + volumen.getVolume() + " - " + volumen.getYear());

        // Añadir animación
        convertView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));

        return convertView;
    }
}