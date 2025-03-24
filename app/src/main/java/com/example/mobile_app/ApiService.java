package com.example.mobile_app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("ws/journals.php")
    Call<List<Revista>> getRevistas();

    @GET("ws/issues.php")
    Call<List<Volumen>> getVolumenes(@Query("j_id") String journalId);

    @GET("ws/pubs.php")
    Call<List<Paper>> getPapers(@Query("i_id") String issueId);
}