package com.pratap.endlessrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Libraries.RequestCallback;

public class    MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    private CrazyDealDataAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private Button button;
    private static int displayedposition = 0;

    private int crazyDealsCountLimit;
    private List<CrazyData> crazyDataList;

    protected Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crazy_deal_recycler_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        crazyDataList = new ArrayList<>();
        handler = new Handler();

        loadInitialData();

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();


                displayedposition = llm.findFirstVisibleItemPosition();


            }
        });


        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CrazyDealDataAdapter(crazyDataList, mRecyclerView, getBaseContext());

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //add null , so the adapter will check view_type and show progress bar at bottom
                crazyDataList.add(null);
                mAdapter.notifyItemInserted(crazyDataList.size() -1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //   remove progress item
                        crazyDataList.remove(crazyDataList.size() - 1);

                        //add items one by one
                        int start = crazyDataList.size();
                        if(start <= crazyDealsCountLimit) {
                            return;
                        } else {
                            loadData(start, 10);
                        }
                    }
                }, 2000);
            }
        });
    }

    private void loadInitialData() {
        CrazyDealRequest crazyDealRequest = new CrazyDealRequest();

        crazyDealRequest.getCrazyDeals(this,
                new RequestCallback<CrazyData[]>() {
                    @Override
                    public void onSuccess(CrazyData[] data) {
                        crazyDataList.addAll(Arrays.asList(data));
                        mRecyclerView.setAdapter(mAdapter);

                        if (crazyDataList.isEmpty()) {
                            mRecyclerView.setVisibility(View.GONE);
                            tvEmptyView.setVisibility(View.VISIBLE);
                        } else {
                            mRecyclerView.setVisibility(View.VISIBLE);
                            tvEmptyView.setVisibility(View.GONE);
                        }
                    }
                }, new LimitModel(0, 10));

        crazyDealRequest.getCrazyDealsCount(this, new RequestCallback<CountModel>() {
            @Override
            public void onSuccess(CountModel data) {
                crazyDealsCountLimit = data.getCount();
            }
        });
    }

    // load after initial load data
    private void loadData(final int index, final int count) {

            new CrazyDealRequest().getCrazyDeals(this,
                    new RequestCallback<CrazyData[]>() {
                        @Override
                        public void onSuccess(CrazyData[] data) {
                            crazyDataList.addAll(Arrays.asList(data));


                            mRecyclerView.setAdapter(mAdapter);

                            if (crazyDataList.isEmpty()) {
                                mRecyclerView.setVisibility(View.GONE);
                                tvEmptyView.setVisibility(View.VISIBLE);
                            } else {
                                mRecyclerView.setVisibility(View.VISIBLE);
                                tvEmptyView.setVisibility(View.GONE);
                            }

                            LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                            llm.scrollToPositionWithOffset(displayedposition+1 , crazyDataList.size());
                            mAdapter.setLoaded();
                            // mAdapter.notifyItemInserted(index - 1);
                            //mAdapter.notifyItemRangeInserted(index - 1, count);
                            //mAdapter.notifyItemRemoved(crazyDataList.size());
                        }
                    }, new LimitModel(index, count));

    }
}