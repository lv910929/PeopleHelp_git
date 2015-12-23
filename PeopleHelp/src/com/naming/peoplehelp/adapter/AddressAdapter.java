package com.naming.peoplehelp.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.AddressActivity;
import com.naming.peoplehelp.activity.AddressConfigActivity;
import com.naming.peoplehelp.entity.Address;

public class AddressAdapter extends BaseAdapter{
	
	private List<Address> addresses;
	
	private Context context;

	public AddressAdapter(List<Address> addresses, Context context) {
		super();
		this.addresses = addresses;
		this.context = context;
	}

	@Override
	public int getCount() {
		return addresses.size();
	}

	@Override
	public Object getItem(int position) {
		return addresses.get(position);
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
			view=View.inflate(context, R.layout.list_address_item, null);
			holder = new ViewHolder();
			holder.userNameLabel = (TextView) view.findViewById(R.id.label_user_name);
			holder.phoneNumberLabel =  (TextView) view.findViewById(R.id.label_phone_number);
			holder.addressNameLabel=(TextView) view.findViewById(R.id.label_address_name);
			holder.editAddressImage=(ImageView) view.findViewById(R.id.image_edit_address);
			view.setTag(holder);
		}
		final Address address=addresses.get(position);
		holder.userNameLabel.setText(address.getUserName());
		holder.phoneNumberLabel.setText(address.getPhoneNumber());
		holder.addressNameLabel.setText(address.getAddressName()+address.getAddressDetail());
		holder.editAddressImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.image_edit_address:
					Intent intent=new Intent(context, AddressConfigActivity.class);
					Bundle mBundle = new Bundle();
					mBundle.putSerializable("address", address);
					intent.putExtras(mBundle);
					((Activity) context).startActivityForResult(intent, AddressActivity.ADDRESS_CONFIG);
					break;
				}
			}
		});
		return view;
	}

	static class ViewHolder{
		
		TextView userNameLabel;
		TextView phoneNumberLabel;
		TextView addressNameLabel;
		ImageView editAddressImage;
	}
}
