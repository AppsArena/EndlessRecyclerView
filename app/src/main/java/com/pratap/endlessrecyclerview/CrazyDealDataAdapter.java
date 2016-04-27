package com.pratap.endlessrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CrazyDealDataAdapter extends RecyclerView.Adapter {
	private final int VIEW_ITEM = 1;
	private final int VIEW_PROG = 0;

	private List<CrazyData> crazyList;

	// The minimum amount of items to have below your current scroll position
	// before loading more.
	private int visibleThreshold = 5;
	private int lastVisibleItem, totalItemCount;
	private boolean loading;
	private OnLoadMoreListener onLoadMoreListener;
	private Context context;

	

	public CrazyDealDataAdapter(List<CrazyData> crazy, RecyclerView recyclerView, Context context)  {

		this.context=context;
		crazyList = crazy;

		if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

			final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
					.getLayoutManager();


					recyclerView
					.addOnScrollListener(new RecyclerView.OnScrollListener() {
						@Override
						public void onScrolled(RecyclerView recyclerView,
											   int dx, int dy) {
							super.onScrolled(recyclerView, dx, dy);

							totalItemCount = linearLayoutManager.getItemCount();
							lastVisibleItem = linearLayoutManager
									.findLastVisibleItemPosition();
							if (!loading
									&& totalItemCount <= (lastVisibleItem + visibleThreshold)) {
								// End has been reached
								// Do something
								if (onLoadMoreListener != null) {
									onLoadMoreListener.onLoadMore();
								}
								loading = true;
							}
						}
					});
		}
	}

	@Override
	public int getItemViewType(int position) {
		return crazyList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
			int viewType) {
		RecyclerView.ViewHolder vh;
		if (viewType == VIEW_ITEM) {
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.list_row, parent, false);

			vh = new CrazyDealViewHolder(v);
		} else {
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.progress_item, parent, false);

			vh = new ProgressViewHolder(v);
		}
		return vh;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (holder instanceof CrazyDealViewHolder) {
			
			CrazyData singleCrazy= (CrazyData) crazyList.get(position);

			((CrazyDealViewHolder) holder).mBuyButton.setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View view) {
							Toast.makeText(context,"Crazy Deal",Toast.LENGTH_SHORT).show();
						}
					}
			);
			//((CrazyDealViewHolder) holder).mImageLarge.setImageResource(singleCrazy.getLargeImage());
			//Picasso.with(context).load("http://www.ajkerdeal.com/Images/Deals/121636_29.03.20162903/1.jpg").placeholder(R.drawable.heart).into(((CrazyDealViewHolder)holder).mImageLarge);
			Picasso.with(context).load("http://www.ajkerdeal.com/Images/Deals/" + singleCrazy.getFolderName() + "/1.jpg").into(((CrazyDealViewHolder)holder).mImageLarge);



			((CrazyDealViewHolder) holder).mTextView.setText(singleCrazy.getDealTitle());
			
			((CrazyDealViewHolder) holder).crazyData= singleCrazy;
			
		} else {
			((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
		}
	}

	public void setLoaded() {
		loading = false;
	}

	@Override
	public int getItemCount() {
		return crazyList.size();
	}

	public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
		this.onLoadMoreListener = onLoadMoreListener;
	}


	//
	public static class CrazyDealViewHolder extends RecyclerView.ViewHolder {


		public ImageView mImageLarge;

		public Button mBuyButton;
		
		public CrazyData crazyData;

		public TextView mTextView;

		public CrazyDealViewHolder(View v) {
			super(v);

			mTextView=(TextView)v.findViewById(R.id.textView);

			mImageLarge=(ImageView)v.findViewById(R.id.imageLargeV);

			mBuyButton=(Button)v.findViewById(R.id.buyButton);

			
			/*v.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(v.getContext(),
							"OnClick :" + crazyData.getName() + " \n "+student.getEmailId(),
							Toast.LENGTH_SHORT).show();

				}
			});*/
		}
	}

	public static class ProgressViewHolder extends RecyclerView.ViewHolder {
		public ProgressBar progressBar;

		public ProgressViewHolder(View v) {
			super(v);
			progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
		}
	}
}