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
		cities.add(new City(1, "����"));
		cities.add(new City(2, "�Ϻ�"));
		cities.add(new City(3, "����"));
		cities.add(new City(4, "����"));
		cities.add(new City(5, "����"));
		cities.add(new City(6, "�ɶ�"));
		cities.add(new City(7, "����"));
		cities.add(new City(8, "�Ͼ�"));
		cities.add(new City(9, "����"));
		cities.add(new City(10, "�人"));
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("�����б�");
		
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
