package com.example.newsapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI{
	@GET
	Call<NewsModal> getAllNews(@Url string url);

	@GET
	Call<NewsModal>getNewsByCategory(@Url string url);

}
