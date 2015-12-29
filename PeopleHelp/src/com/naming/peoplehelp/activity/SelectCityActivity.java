package com.naming.peoplehelp.activity;

import java.util.ArrayList;
import java.util.List;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.adapter.CityAdapter;
import com.naming.peoplehelp.entity.City;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SelectCityActivity extends BaseActivity implements OnItemClickListener{
	
	private List<City> cities;
	
	private TextView titleLabel;
	
	private ListView cityListView;
	private CityAdapter cityAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city_select);
		initData();
		initUI();
	}
	
	private void initData(){
		
		cities=new ArrayList<City>();
		cities.add(new City(1, "北京"));
		cities.add(new City(2, "上海"));
		cities.add(new City(3, "广州"));
		cities.add(new City(4, "深圳"));
		cities.add(new City(5, "苏州"));
		cities.add(new City(6, "成都"));
		cities.add(new City(7, "重庆"));
		cities.add(new City(8, "南京"));
		cities.add(new City(9, "厦门"));
		cities.add(new City(10, "武汉"));
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("城市列表");
		
		cityListView=(ListView) findViewById(R.id.listView_city);
		cityAdapter=new CityAdapter(SelectCityActivity.this, cities);
		cityListView.setAdapter(cityAdapter);
		cityListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		saveCity(cities.get(position));
		finish();
	}
	
	private void saveCity(City city){
		Editor editor = ContextApplication.sp.edit();
		editor.putInt("cityId", city.getCityId());
		editor.putString("cityName", city.getCityName());
		editor.commit();
	}

}
