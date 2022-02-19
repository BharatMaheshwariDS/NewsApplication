package  com.example.newsapplication;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.reclerview.widget.RecyclerView;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>{
	private ArrayList<CategoryRVModal> categoryRVModals;
	private Context context;
	private CategoryClickInterface categoryClickInterface;
	
	public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context,CategoryClickListener categoryClickListener){
				this.categoryRVModals=categoryRVModals;
				this.context=context;
			this.categoryClickListener=categoryClickListener;
						}
	@NunNull
	@Override
	public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
 	View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
	return new CategoryRVAdapter.ViewHolder(view);
 	}
	@Override
	public void onBindViewHolder(@NonNull NewsRVAdapter.ViewHolder holder, int position){
		CategoryRVModal categoryRVModal = categoryRVModals.get(position);
		holder.categoryTV.setText(categoryRVModal.getCategory());
		picasso.get().load(categoryRVModal.getUrlToImage()).into(holder.categoryIV);
		holder.itemView.setOnClickListener(new View.OnClickListener(){
		@Override
		public void onClick(View v){
			categoryClickInterface.onCategoryClick(position);
			}
		});
	 }
	@Override
	public int getItemCount(){
	return categoryRVModals.size();
	}
	public interface CategoryClickInterface{
		void onCategoryClick(int position);
	}
	
	public class viewHolder extends RecyclerView.ViewHolder{
	private TextView categoryTV;
	private ImageView categoryIV;
	public ViewHolder(@NonNull View itemView){
	super(itemView);
	categoryTV = itemView.findViewById(R.id.idTVCategory);
	categoryIV = itemView.findViewById(R.id.idIVCategory);
  	 }
	}
}