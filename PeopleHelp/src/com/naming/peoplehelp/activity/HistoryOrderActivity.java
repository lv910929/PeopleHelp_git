package com.naming.peoplehelp.activity;

import java.util.ArrayList;
import java.util.List;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.adapter.OrderAdapter;
import com.naming.peoplehelp.entity.Order;
import com.naming.peoplehelp.view.dialog.AlertDialog;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryOrderActivity extends BaseActivity implements OnClickListener{
	
	private TextView titleLabel;
	private TextView otherButton;
	
	private LinearLayout noHistoryOrderLayout;
	private ListView historyOrderListView;
	private OrderAdapter historyOrderAdapter;
	
	private List<Order> historyOrders;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_order);
		initUI();
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		otherButton=(TextView) findViewById(R.id.btn_other);
		
		titleLabel.setText("历史订单");
		otherButton.setText("清空");
		
		noHistoryOrderLayout=(LinearLayout) findViewById(R.id.layout_no_history_order);
		historyOrderListView=(ListView) findViewById(R.id.listView_history_order);
		
		loadListView();
		otherButton.setOnClickListener(this);
	}
	
	private void loadListView(){
		historyOrders=new ArrayList<Order>();
		if (ContextApplication.minePhone.equals("15501690442")) {
			historyOrders.add(new Order("111", "15501690442", 0, "上海市浦东新区软件园一期306号","测试测试", "2015-12-10 15:00:00", 2));
			historyOrders.add(new Order("112", "15501690442", 1, "上海市浦东新区软件园一期306号","测试测试", "2015-12-11 15:00:00", 2));
		}
		if (historyOrders.size()>0) {
			historyOrderListView.setVisibility(View.VISIBLE);
			noHistoryOrderLayout.setVisibility(View.GONE);
			historyOrderAdapter=new OrderAdapter(historyOrders, HistoryOrderActivity.this);
			historyOrderListView.setAdapter(historyOrderAdapter);
		}else {
			noHistoryOrderLayout.setVisibility(View.VISIBLE);
			historyOrderListView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_other:
			if (historyOrders.size()>0) {
				showDialog("您确定要清空历史订单？");
			}else {
				Toast.makeText(getApplicationContext(), "您没有历史订单", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}
	
	private void showDialog(String message) {
		new AlertDialog(HistoryOrderActivity.this).builder().setTitle("提示")
				.setCancelable(true).setMsg(message)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {}
				}).show();
	}

}
