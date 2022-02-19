package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements CategoryRVAdapter.CategoryClickInterface{
	private RecyclerView newRV,categoryRV;
	private ProgressBar loadingPB;
	private ArrayList<Articles> articlesArrayList;
	private ArrayList<CategoryRVModal> categoryRVModalArrayList;
	private CategoryRVAdapter categoryRVAdapter;
	private NewsRVAdapter newsRVAdapter;	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	newsRV= findViewById(R.id.idRVNews);
	categoryRV = findViewById(R.id.idRVCategories);
	loadingPB= findViewById(R.id.idPBLoading);
	articlesArrayList = new ArrayList<>();
	categoryRVModalArrayList = new ArrayList<>();
	newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
	categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
	newsRV.setLayoutManager(new LinearLayoutManager(this));
	newsRV.setAdapter(newsRVAdapter);
	categoryRV.setAdapter(categoryRVAdapter);
	getCategories();
	getNews("All");
	newsRVAdapter.notifyDataSetChanged();
    }

	private void getCategories(){
	categoryRVModalArrayList.add(new CategoryRVModal("All","https://media.istockphoto.com/photos/abstract-digital-news-concept-picture-id1290904409?b=1&k=20&m=1290904409&s=170667a&w=0&h=6khncht98kwYG-l7bdeWfBNs_GGcG1pDqzLb6ZXhh7I="));
	categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1550751827-4bd374c3f58b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjB8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"));
	categoryRVModalArrayList.add(new CategoryRVModal("Science","https://media.istockphoto.com/photos/medical-science-laboratory-handsome-latin-male-scientist-writes-picture-id1346675624?b=1&k=20&m=1346675624&s=170667a&w=0&h=w5wR6TwSkTO46-i9ooqVTUwmjAkbzmnJ55zysWSBqWk="));
	categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://media.istockphoto.com/photos/various-sport-equipments-on-grass-picture-id949190736?b=1&k=20&m=949190736&s=170667a&w=0&h=f3ofVqhbmg2XSVOa3dqmvGtHc4VLA_rtbboRGaC8eNo="));
	categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1493612276216-ee3925520721?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
	categoryRVModalArrayList.add(new CategoryRVModal("Bussiness","https://media.istockphoto.com/photos/businessman-trading-online-stock-market-on-teblet-screen-digital-picture-id1311598658?b=1&k=20&m=1311598658&s=170667a&w=0&h=Ln_dpeXRkCDCZjuqOe2r7AlWP29xHFbc9wyKzxajloA="));
	categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
	categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"));
	categoryRVAdapter.notifyDataSetChanged();
	}

	private void getNews(String category){
		loadingPB.setVisibility(View.VISIBLE);
		articlesArrayList.clear();
		String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apikey=13529875ccd1462ebbcc8cd686c613a3";
		String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=13529875ccd1462ebbcc8cd686c613a3";
		String BASE_URL = "https://newsapi.org/";
		Retrofit retrofit = new Retrofi.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build();
		RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
		Call<NewsModal> call;
		if(category.equals("All")){
			call = retrofitAPI.getAllNews(url);
		}else{
			call = retrofitAPI.getNewsByCategory(categoryURL);
		}
		
		call.enqueue(new Callback<NewsModal>(){
		@Override
		public void onResponse(Call<NewsModal> call, Response<NewsModal> response){
			NewsModal newsModal = response.body();
			loadingPB.setVisibility(View.GONE);
			ArrayList<Articles> articles = newsModal.getArticles();
			for(int i=0;i<articles.size();i++){
				articlesArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescripton(),articles.get(i).getUrl(), articles.get(i).getContent()));
			}
			newsRVAdapter.notifyDataSetChanged();
		}
		@Override
		public void onFailure(Call <NewsModal> call, Throwable t){
		Toast.makeText(MainActivity.this," Fail to get News", Toast.LENGTH_SHORT).show();	
		}	
	});
	@Override
	public void onCategoryClick(int position){
		String category = categoryRCVModalArrayList.get(position).getCategory();
		getNews(category);
					}
}