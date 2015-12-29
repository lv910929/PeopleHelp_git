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
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.entity.City;

public class CityAdapter extends BaseAdapter{
	
	private Context context;
	
	private List<City> cities;

	public CityAdapter(Context context, List<City> cities) {
		super();
		this.context = context;
		this.cities = cities;
	}

	@Override
	public int getCount() {
		return cities.size();
	}

	@Override
	public Object getItem(int position) {
		return cities.get(position);
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
			view=View.inflate(context, R.layout.list_city_item, null);
			holder = new ViewHolder();
			holder.cityNameLabel = (TextView) view.findViewById(R.id.label_city_name);
			holder.citySelectedImage=(ImageView) view.findViewById(R.id.image_city_selected);
			view.setTag(holder);
		}
		City city=cities.get(position);
		holder.cityNameLabel.setText(city.getCityName());
		if (city.getCityId()==ContextApplication.sp.getInt("cityId", 0)) {
			holder.citySelectedImage.setVisibility(View.VISIBLE);
		}else {
			holder.citySelectedImage.setVisibility(View.GONE);
		}
		return view;
	}
	
	static class ViewHolder{
		
		TextView cityNameLabel;
		ImageView citySelectedImage;
	}

}
