package com.naming.peoplehelp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.AddressConfigActivity;
import com.naming.peoplehelp.entity.Order;
import com.naming.peoplehelp.view.dialog.AlertDialog;

public class OrderAdapter extends BaseAdapter{
	
	public static final String[] ORDER_STATE_STRINGS={"等待支付定金","正在进行","已经完成"};
	
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
			holder.orderTypeLabel = (TextView) view.findViewById(R.id.label_order_type);
			holder.orderTimeLabel = (TextView) view.findViewById(R.id.label_order_time);
			holder.orderStateLabel =  (TextView) view.findViewById(R.id.label_order_state);
			holder.orderAddressLabel=(TextView) view.findViewById(R.id.label_order_address);
			holder.orderTypeImage=(ImageView) view.findViewById(R.id.image_order_type);
			holder.orderBeforeButton=(Button) view.findViewById(R.id.btn_order_before);
			holder.orderAfterButton=(Button) view.findViewById(R.id.btn_order_after);
			view.setTag(holder);
		}
		final Order order = orders.get(position);
		holder.orderTypeLabel.setText(HomeGridAdapter.homeTxts[order.getOrder_type()]);
		holder.orderTimeLabel.setText(order.getOrder_time());
		holder.orderStateLabel.setText(ORDER_STATE_STRINGS[order.getOrder_state()]);
		holder.orderAddressLabel.setText(order.getOrder_address());
		holder.orderTypeImage.setImageResource(HomeGridAdapter.homeIcons[order.getOrder_type()]);
		if (order.getOrder_state()==1) {
			holder.orderBeforeButton.setText("付尾款");
		}else if (order.getOrder_state()==2) {
			holder.orderBeforeButton.setVisibility(View.GONE);
			holder.orderAfterButton.setText("再来一单");
		}
		holder.orderBeforeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		holder.orderAfterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (order.getOrder_state()==0||order.getOrder_state()==1) {
					showDialog("您确定要取消这个订单？");
				}
			}
		});
		return view;
	}
	
	static class ViewHolder{
		
		TextView orderTypeLabel;
		TextView orderTimeLabel;
		TextView orderStateLabel;
		TextView orderAddressLabel;
		ImageView orderTypeImage;
		Button orderBeforeButton;
		Button orderAfterButton;
	}
	
	private void showDialog(String message) {
		new AlertDialog(context).builder().setTitle("提示")
				.setCancelable(true).setMsg(message)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
						
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {}
				}).show();
	}

}
