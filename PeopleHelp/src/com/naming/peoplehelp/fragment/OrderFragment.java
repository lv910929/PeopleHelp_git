package com.naming.peoplehelp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.HistoryOrderActivity;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.adapter.OrderAdapter;
import com.naming.peoplehelp.entity.Order;
import com.naming.peoplehelp.view.RefreshLayout;
import com.naming.peoplehelp.view.RefreshLayout.OnLoadListener;

public class OrderFragment extends BaseFragment implements OnClickListener{
	
	private TextView historyOrderButton;
	private LinearLayout noLoginLayout;
	private RelativeLayout orderLayout;
	private LinearLayout noOrderLayout;
	private Button redirectloginButton;
	private RefreshLayout orderRefreshLayout;
	private ListView orderListView;
	private OrderAdapter orderAdapter;
	
	private List<Order> orders;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_order, container,false);
		return view;
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initUI();
	}
	
	private void initUI(){
		
		orderRefreshLayout=(RefreshLayout) getActivity().findViewById(R.id.refresh_order);
		historyOrderButton=(TextView) getActivity().findViewById(R.id.btn_history_order);
		noLoginLayout=(LinearLayout) getActivity().findViewById(R.id.layout_no_login);
		redirectloginButton=(Button) getActivity().findViewById(R.id.btn_redirect_login);
		orderLayout=(RelativeLayout) getActivity().findViewById(R.id.layout_order);
		noOrderLayout=(LinearLayout) getActivity().findViewById(R.id.layout_no_order);
		orderListView=(ListView) getActivity().findViewById(R.id.listView_order);
		
		checkLoginState();
		historyOrderButton.setOnClickListener(this);
		redirectloginButton.setOnClickListener(this);
	}
	
	private void checkLoginState(){
		if (ContextApplication.hasLogin) {
			orderLayout.setVisibility(View.VISIBLE);
			noLoginLayout.setVisibility(View.GONE);
			orderRefreshLayout.setColorScheme(R.color.blue_bg);
			orderRefreshLayout.post(new Runnable() {
				@Override
				public void run() {
					orderRefreshLayout.setRefreshing(true);
					initData();
					orderRefreshLayout.setRefreshing(false);
				}
			});
			refreshListView();
		}else {
			noLoginLayout.setVisibility(View.VISIBLE);
			orderLayout.setVisibility(View.GONE);
		}
	}
	
	private void initData(){
		orders=new ArrayList<Order>();
		if (ContextApplication.minePhone.equals("15501690442")) {
			orders.add(new Order("111", "15501690442", 0, "上海市浦东新区软件园一期306号","测试测试", "2015-12-16 15:00:00", 0));
			orders.add(new Order("112", "15501690442", 1, "上海市浦东新区软件园一期306号","测试测试", "2015-12-17 15:00:00", 1));
			orders.add(new Order("113", "15501690442", 2, "上海市浦东新区软件园一期306号","测试测试", "2015-12-18 15:00:00", 2));
		}
		if (orders.size()>0) {
			orderListView.setVisibility(View.VISIBLE);
			noOrderLayout.setVisibility(View.GONE);
			orderAdapter=new OrderAdapter(orders, getActivity());
			orderListView.setAdapter(orderAdapter);
		}else {
			noOrderLayout.setVisibility(View.VISIBLE);
			orderListView.setVisibility(View.GONE);
		}
	}
	
	private void refreshListView(){
		//下拉刷新
		orderRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				orderRefreshLayout.postDelayed(new Runnable() {
					@Override
					public void run() {
						Order order =new Order("114", "15501690442", 3, "苏州市工业园区国际科技园二期503","测试测试", "2016-01-01 15:00:00", 0);
						orders.add(0, order);
						orderAdapter.notifyDataSetChanged();
						orderRefreshLayout.setRefreshing(false);
					}
				}, 1000);
			}
		});
		//上拉刷新
		orderRefreshLayout.setOnLoadListener(new OnLoadListener() {
			
			@Override
			public void onLoad() {
				orderRefreshLayout.postDelayed(new Runnable() {
					@Override
					public void run() {
						Order order =new Order("102", "15501690442", 0, "常州市钟楼区高新技术创业服务中心503","测试测试", "2015-12-01 15:00:00", 0);
						orders.add(order);
						orderAdapter.notifyDataSetChanged();
						orderRefreshLayout.setLoading(false);
					}
				}, 1000);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_history_order:
			if (ContextApplication.hasLogin) {
				startActivity(new Intent(getActivity(), HistoryOrderActivity.class));
			}else {
				redirtToLogin();
			}
			break;
		case R.id.btn_redirect_login:
			if (!ContextApplication.hasLogin) {
				redirtToLogin();
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BaseFragment.LOGIN_CODE
				&& resultCode == getActivity().RESULT_OK) {
			checkLoginState();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		checkLoginState();
	}

}
