package com.naming.peoplehelp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.view.BaseViewHolder;

public class HomeGridAdapter extends BaseAdapter {
	
	private Context mContext;

	public final static int[] homeIcons = { R.drawable.home_clean_normal,
			R.drawable.home_sofa_normal, R.drawable.home_plaster_normal,
			R.drawable.home_door_normal, R.drawable.home_fix_normal,
			R.drawable.home_security_normal, R.drawable.home_move_normal,
			R.drawable.home_search_normal, R.drawable.home_more_normal };

	public final static String[] homeTxts = { "清洁服务", "沙发保养", "粉刷服务", "开锁服务",
			"维修服务", "新房开荒", "搬家服务", "失物找寻", "更多服务" };

	public HomeGridAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return homeIcons.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_home_item, parent, false);
		}
		TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
		ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
		iv.setBackgroundResource(homeIcons[position]);
		tv.setText(homeTxts[position]);
		return convertView;
	}

}
