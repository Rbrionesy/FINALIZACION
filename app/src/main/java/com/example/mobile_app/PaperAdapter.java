package com.example.mobile_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PaperAdapter extends ArrayAdapter<Paper> {
    private PaperActivity activity;

    public PaperAdapter(Context context, ArrayList<Paper> papers) {
        super(context, 0, papers);
        this.activity = (PaperActivity) context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_paper, parent, false);
        }

        Paper paper = getItem(position);
        if (paper == null) {
            return convertView;
        }

        TextView title = convertView.findViewById(R.id.paperTitle);
        TextView authors = convertView.findViewById(R.id.paperAuthors);
        Button pdfButton = convertView.findViewById(R.id.pdfButton);
        Button htmlButton = convertView.findViewById(R.id.htmlButton);
        Button doiButton = convertView.findViewById(R.id.doiButton);

        title.setText(paper.getTitle() != null ? paper.getTitle() : "Sin título");

        // Concatenar los nombres de los autores
        StringBuilder authorsText = new StringBuilder();
        if (paper.getAuthors() != null) {
            for (Paper.Author author : paper.getAuthors()) {
                if (author.getNombres() != null) {
                    if (authorsText.length() > 0) {
                        authorsText.append(", ");
                    }
                    authorsText.append(author.getNombres());
                }
            }
        }
        authors.setText(authorsText.length() > 0 ? authorsText.toString() : "Sin autores");

        // Buscar las URLs de PDF y HTML
        String pdfUrl = null;
        String htmlUrl = null;
        if (paper.getGaleys() != null) {
            for (Paper.Galley galley : paper.getGaleys()) {
                if ("PDF".equals(galley.getLabel())) {
                    pdfUrl = galley.getUrlViewGalley();
                } else if ("HTML".equals(galley.getLabel())) {
                    htmlUrl = galley.getUrlViewGalley();
                }
            }
        }

        // Configurar los botones
        final String finalPdfUrl = pdfUrl;
        pdfButton.setEnabled(finalPdfUrl != null);
        pdfButton.setOnClickListener(v -> {
            if (finalPdfUrl != null) {
                activity.downloadPdf(finalPdfUrl);
            }
        });

        final String finalHtmlUrl = htmlUrl;
        htmlButton.setEnabled(finalHtmlUrl != null);
        htmlButton.setOnClickListener(v -> {
            if (finalHtmlUrl != null) {
                activity.openHtml(finalHtmlUrl);
            }
        });

        final String doiUrl = paper.getDoi();
        doiButton.setEnabled(doiUrl != null);
        doiButton.setOnClickListener(v -> {
            if (doiUrl != null) {
                activity.openHtml(doiUrl);
            }
        });

        return convertView;
    }
}