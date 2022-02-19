package com.example.newsapplication;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.reclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder>{
	private ArrayList<Articles> articlesArrayList;
	private Context context;
	public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context){
				this.articlesArrayList=articlesArrayList;
				this.context=context;
	@NunNull
	@Override
	public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
 	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
	return new NewsRVAdapter.ViewHolder(view);
 	}
	@Override
	public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position){
		Articles articles = articlesArrayList.get(position);
		holder.subTitleTV.setText(articles.getDescription());
		holder.titleTV.setText(articles.getTitle());
		picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
		holder.itemView.setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v){
			Intent i = new Intent(context, NewsDetailActivity.class);
		i.putExtra("title",articles.getTitle());
		i.putExtra("content",articles.getContent());
		i.putExtra("desc",articles.getDescription());
		i.putExtra("image",articles.getUrlToImage());
		i.putExtra("title",articles.getUrl());
		context.startActivity(i);
			}
		});
	 }
	@Override
	public int getItemCount(){
	return articlesArrayList.size();
	}
	public class viewHolder extends RecyclerView.ViewHolder{
	private TextView titleTV, subTitleTV;
	private ImageView newsIV;
	public ViewHolder(@NonNull View itemView){
	super(itemView);
	titleTV = itemView.findViewById(R.id.idTVNewsHeading);
	subTitleTV = itemView.findViewById(R.id.idTVSubTitle);
	newsIV = itemView.findViewById(R.id.idIVNews);

	}
	}
}