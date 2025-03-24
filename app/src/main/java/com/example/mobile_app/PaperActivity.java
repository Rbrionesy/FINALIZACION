package com.example.mobile_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaperActivity extends AppCompatActivity {

    private static final String TAG = "PaperActivity";
    private ListView paperListView;
    private ArrayList<Paper> paperList;
    private PaperAdapter adapter;
    private TextView revistaNombre;
    private TextView volumenNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        String issueId = getIntent().getStringExtra("issueId");
        String journalName = getIntent().getStringExtra("journalName");
        String volumenName = getIntent().getStringExtra("volumenName");

        Log.d(TAG, "Issue ID recibido: " + issueId);

        if (issueId == null) {
            Toast.makeText(this, "Error: No se recibió el ID del volumen", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Configurar el encabezado
        revistaNombre = findViewById(R.id.revistaNombre);
        volumenNombre = findViewById(R.id.volumenNombre);
        revistaNombre.setText(journalName != null ? journalName : "Revista Desconocida");
        volumenNombre.setText(volumenName != null ? volumenName : "Volumen Desconocido");

        paperListView = findViewById(R.id.paperListView);
        paperList = new ArrayList<>();
        adapter = new PaperAdapter(this, paperList);
        paperListView.setAdapter(adapter);

        loadPapers(issueId);
    }

    private void loadPapers(String issueId) {
        RetrofitClient.getApi().getPapers(issueId).enqueue(new Callback<List<Paper>>() {
            @Override
            public void onResponse(Call<List<Paper>> call, Response<List<Paper>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    paperList.clear();
                    paperList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "Papers cargados: " + paperList.size());
                } else {
                    Toast.makeText(PaperActivity.this, "Error al cargar papers: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Paper>> call, Throwable t) {
                Toast.makeText(PaperActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Fallo de conexión: " + t.getMessage());
            }
        });
    }

    public void downloadPdf(String pdfUrl) {
        Log.d(TAG, "Abriendo URL del PDF: " + pdfUrl);
        openHtml(pdfUrl); // Abrimos la URL en el navegador
    }

    public void openHtml(String url) {
        Log.d(TAG, "Abriendo URL: " + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}