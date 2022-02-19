package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NewsDetailActivity extends AppCompatActivity {
	String title, desc,content,imageURL,url;
	private TextView titleTV, subDescTV, contentTV;
	private ImageView newsIV;
	private Button readNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
	title = getIntent().getStringExtra("title");
	desc = getIntent().getStringExtra("desc");
	content = getIntent().getStringExtra("content");
	imageURL = getIntent().getStringExtra("image");
	url = getIntent().getStringExtra("url");
	titleTV= findViewById(R.id.idTVTitle);
	subDescTV= findViewById(R.id.idTVSubDesc);
	contentTV= findViewById(R.id.idTVContent);
	newsIV= findViewById(R.id.idIVNews);
	readNewsBtn= findViewById(R.id.idBtnReadNews);
	titleTV.setText(title);
	subDescTV.setText(desc);
	contentTV.setText(content);
	Picasso.get().load(imageURL).into(newsIV);
	readNewsBtn.setOnClickListener(new View.OnClickListener(){
	@Override
	public void onClick(View v){
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
			}
	}
    }
}
