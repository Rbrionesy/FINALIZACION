package com.example.mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RevistaAdapter.OnRevistaClickListener {

    private RecyclerView revistaRecyclerView;
    private ArrayList<Revista> revistaList;
    private RevistaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        revistaRecyclerView = findViewById(R.id.revistaRecyclerView);
        revistaList = new ArrayList<>();
        adapter = new RevistaAdapter(this, revistaList, this);
        revistaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        revistaRecyclerView.setAdapter(adapter);

        loadRevistas();
    }

    private void loadRevistas() {
        RetrofitClient.getApi().getRevistas().enqueue(new Callback<List<Revista>>() {
            @Override
            public void onResponse(Call<List<Revista>> call, Response<List<Revista>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    revistaList.clear();
                    revistaList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Error al cargar revistas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Revista>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRevistaClick(Revista revista) {
        Intent intent = new Intent(MainActivity.this, VolumenActivity.class);
        intent.putExtra("journalId", revista.getJournalId());
        intent.putExtra("journalName", revista.getName());
        startActivity(intent);
    }
}