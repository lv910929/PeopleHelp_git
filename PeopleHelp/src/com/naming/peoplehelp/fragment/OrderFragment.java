package com.naming.peoplehelp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class OrderFragment extends BaseFragment implements OnClickListener{
	
	private TextView historyOrderButton;
	private LinearLayout noLoginLayout;
	private RelativeLayout orderLayout;
	private LinearLayout noOrderLayout;
	private Button redirectloginButton;
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
			initData();
		}else {
			noLoginLayout.setVisibility(View.VISIBLE);
			orderLayout.setVisibility(View.GONE);
		}
	}
	
	private void initData(){
		orders=new ArrayList<Order>();
		if (ContextApplication.minePhone.equals("15501690442")) {
			orders.add(new Order("111", "15501690442", 0, "²âÊÔ²âÊÔ", "2015-12-16 15:00:00", 0));
			orders.add(new Order("112", "15501690442", 1, "²âÊÔ²âÊÔ", "2015-12-17 15:00:00", 1));
			orders.add(new Order("113", "15501690442", 2, "²âÊÔ²âÊÔ", "2015-12-18 15:00:00", 2));
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
