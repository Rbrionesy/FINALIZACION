package com.example.mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolumenActivity extends AppCompatActivity {

    private ListView volumenListView;
    private ArrayList<Volumen> volumenList;
    private VolumenAdapter adapter;
    private String journalId;
    private String journalName;
    private TextView revistaNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);

        journalId = getIntent().getStringExtra("journalId");
        journalName = getIntent().getStringExtra("journalName");

        if (journalId == null) {
            Toast.makeText(this, "Error: No se recibió el ID de la revista", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Configurar el encabezado
        revistaNombre = findViewById(R.id.revistaNombre);
        revistaNombre.setText(journalName != null ? journalName : "Revista Desconocida");

        volumenListView = findViewById(R.id.volumenListView);
        volumenList = new ArrayList<>();
        adapter = new VolumenAdapter(this, volumenList);
        volumenListView.setAdapter(adapter);

        loadVolumenes(journalId);

        volumenListView.setOnItemClickListener((parent, view, position, id) -> {
            Volumen volumen = volumenList.get(position);
            Intent intent = new Intent(VolumenActivity.this, PaperActivity.class);
            intent.putExtra("issueId", volumen.getIssueId());
            intent.putExtra("journalName", journalName);
            intent.putExtra("volumenName", "Volumen " + volumen.getVolume());
            startActivity(intent);
        });
    }

    private void loadVolumenes(String journalId) {
        RetrofitClient.getApi().getVolumenes(journalId).enqueue(new Callback<List<Volumen>>() {
            @Override
            public void onResponse(Call<List<Volumen>> call, Response<List<Volumen>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    volumenList.clear();
                    volumenList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(VolumenActivity.this, "Error al cargar volúmenes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Volumen>> call, Throwable t) {
                Toast.makeText(VolumenActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}