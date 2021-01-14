package com.example.uasmobile;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
        private static RetrofitClient instance = null;
        private api.Api myApi;

        private RetrofitClient() {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(api.Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            myApi = retrofit.create(api.Api.class);
        }

        public static synchronized RetrofitClient getInstance() {
            if (instance == null) {
                instance = new RetrofitClient();
            }
            return instance;
        }

        public api.Api getMyApi() {
            return myApi;
        }
}
