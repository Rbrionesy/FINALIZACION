package com.example.mobile_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import okhttp3.ResponseBody;

import java.util.List;

public interface RevistaApi {
    @GET("ws/journals.php")
    Call<List<Revista>> getRevistas();

    @GET("ws/issues.php")
    Call<List<Volumen>> getVolumenes(@Query("j_id") String journalId);

    @GET("ws/pubs.php")
    Call<List<Paper>> getPapers(@Query("i_id") String issueId);

    @GET
    Call<ResponseBody> downloadPdf(@Query("url") String pdfUrl);
}
