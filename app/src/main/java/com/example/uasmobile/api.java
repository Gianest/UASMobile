package com.example.uasmobile;
import com.example.uasmobile.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class api {

    public interface Api {
        String BASE_URL = "https://www.thesportsdb.com/";

        @GET("api/v1/json/1/lookup_all_teams.php")
        Call<List<Response>> getTeam(@Query("id")String id);
        @GET("api/v1/json/1/lookup_all_teams.php")
        Call<List<Response>> getLeague(@Query("id")String id);
    }
}
