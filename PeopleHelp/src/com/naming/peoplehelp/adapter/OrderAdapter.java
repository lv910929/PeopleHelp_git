package com.naming.peoplehelp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.entity.Order;

public class OrderAdapter extends BaseAdapter{
	
	public static final String[] ORDER_STATE_STRINGS={"尚未开始","正在进行","已经完成"};
	
	private List<Order> orders;
	
	private Context context;

	public OrderAdapter(List<Order> orders, Context context) {
		super();
		this.orders = orders;
		this.context = context;
	}

	@Override
	public int getCount() {
		return orders.size();
	}

	@Override
	public Object getItem(int position) {
		return orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view;
		ViewHolder holder;
		if (convertView != null && convertView instanceof RelativeLayout) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}else {
			view=View.inflate(context, R.layout.list_order_item, null);
			holder = new ViewHolder();
			holder.orderTimeLabel = (TextView) view.findViewById(R.id.label_order_time);
			holder.orderStateLabel =  (TextView) view.findViewById(R.id.label_order_state);
			holder.orderDetailLabel=(TextView) view.findViewById(R.id.label_order_detail);
			holder.orderTypeImage=(ImageView) view.findViewById(R.id.image_order_type);
			view.setTag(holder);
		}
		Order order = orders.get(position);
		holder.orderTimeLabel.setText(order.getOrder_time());
		holder.orderStateLabel.setText(ORDER_STATE_STRINGS[order.getOrder_state()]);
		holder.orderDetailLabel.setText(order.getOrder_detail());
		holder.orderTypeImage.setImageResource(HomeGridAdapter.homeIcons[order.getOrder_type()]);
		return view;
	}
	
	static class ViewHolder{
		
		TextView orderTimeLabel;
		TextView orderStateLabel;
		TextView orderDetailLabel;
		ImageView orderTypeImage;
	}

}
