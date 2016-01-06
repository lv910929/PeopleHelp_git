package com.naming.peoplehelp.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.task.AbTaskQueue;
import com.naming.peoplehelp.R;
import com.naming.peoplehelp.adapter.ImageListAdapter;
import com.naming.peoplehelp.view.RefreshLayout;
import com.naming.peoplehelp.view.RefreshLayout.OnLoadListener;

public class LifeFragment_2 extends BaseFragment {

	private List<Map<String, Object>> list = null;
	private List<Map<String, Object>> newList = null;

	private RefreshLayout myRefreshListView;
	private ListView newsListView = null;
	private ImageListAdapter myListViewAdapter = null;

	private AbTaskQueue mAbTaskQueue = null;
	private ArrayList<String> mPhotoList = new ArrayList<String>();

	private int currentPage = 1;
	private int total = 50;
	private int pageSize = 5;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_life_2, null);
		initUI(view);
		return view;
	}

	private void initData() {
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i3/13215035600700175/T1C2mzXthaXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i2/13215025617307680/T1AQqAXqpeXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i1/13215035569460099/T16GuzXs0cXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i2/13215023694438773/T1lImmXElhXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i3/13215023521330093/T1BWuzXrhcXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i4/13215035563144015/T1Q.eyXsldXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mPhotoList
				.add("http://img01.taobaocdn.com/bao/uploaded/i3/13215023749568975/T1UKWCXvpXXXXXXXXX_!!0-item_pic.jpg_230x230.jpg");
		mAbTaskQueue = AbTaskQueue.getInstance();
	}

	private void initUI(View view) {

		newsListView = (ListView) view.findViewById(R.id.listView_news);
		// 获取RefreshLayout实例
		myRefreshListView = (RefreshLayout) view
				.findViewById(R.id.refresh_layout);
		// 设置下拉刷新时的颜色值,颜色值需要定义在xml中
		myRefreshListView.setColorScheme(android.R.color.holo_blue_dark);

	}

	private void loadListView() {
		// ListView数据
		list = new ArrayList<Map<String, Object>>();

		// 使用自定义的Adapter
		myListViewAdapter = new ImageListAdapter(getActivity(), list,
				R.layout.list_items, new String[] { "itemsIcon", "itemsTitle",
						"itemsText" }, new int[] { R.id.itemsIcon,
						R.id.itemsTitle, R.id.itemsText });
		newsListView.setAdapter(myListViewAdapter);
		// item被点击事件
		newsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
		// 定义两种查询的事件
		final AbTaskItem item1 = new AbTaskItem();
		item1.listener = new AbTaskListener() {

			@Override
			public void update() {
				list.clear();
				if (newList != null && newList.size() > 0) {
					list.addAll(newList);
					myListViewAdapter.notifyDataSetChanged();
					newList.clear();
				}
				myRefreshListView.setRefreshing(false);
			}

			@Override
			public void get() {
				try {
					Thread.sleep(1000);
					currentPage = 1;
					newList = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = null;

					for (int i = 0; i < pageSize; i++) {
						map = new HashMap<String, Object>();
						map.put("itemsIcon", mPhotoList.get(new Random()
								.nextInt(mPhotoList.size())));
						map.put("itemsTitle", "[Fragment1]" + (i + 1));
						map.put("itemsText", "[Fragment1]..." + (i + 1));
						newList.add(map);
					}
				} catch (Exception e) {
				}
			};
		};

		final AbTaskItem item2 = new AbTaskItem();
		item2.listener = new AbTaskListener() {

			@Override
			public void update() {
				if (newList != null && newList.size() > 0) {
					list.addAll(newList);
					myListViewAdapter.notifyDataSetChanged();
					newList.clear();
				}
				myRefreshListView.setLoading(false);
			}

			@Override
			public void get() {
				try {
					currentPage++;
					Thread.sleep(1000);
					newList = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = null;

					for (int i = 0; i < pageSize; i++) {
						map = new HashMap<String, Object>();
						map.put("itemsIcon", mPhotoList.get(new Random()
								.nextInt(mPhotoList.size())));
						map.put("itemsTitle", "item上拉"
								+ ((currentPage - 1) * pageSize + (i + 1)));
						map.put("itemsText", "item上拉..."
								+ ((currentPage - 1) * pageSize + (i + 1)));
						if ((list.size() + newList.size()) < total) {
							newList.add(map);
						}
					}
				} catch (Exception e) {
					currentPage--;
					newList.clear();
				}
			};
		};

		// 自动刷新
		myRefreshListView.post(new Runnable() {
			@Override
			public void run() {
				myRefreshListView.setRefreshing(true);
				initData();
				loadListView();
				myRefreshListView.setRefreshing(false);
			}
		});
		myRefreshListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				mAbTaskQueue.execute(item1);
			}
		});

		myRefreshListView.setOnLoadListener(new OnLoadListener() {

			@Override
			public void onLoad() {
				mAbTaskQueue.execute(item2);
			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
