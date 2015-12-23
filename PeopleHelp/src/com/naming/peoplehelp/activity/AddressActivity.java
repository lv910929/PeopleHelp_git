package com.naming.peoplehelp.activity;

import java.util.ArrayList;
import java.util.List;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.adapter.AddressAdapter;
import com.naming.peoplehelp.entity.Address;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class AddressActivity extends BaseActivity implements OnClickListener {
	
	public static final Integer ADDRESS_CONFIG = 2;
	
	private List<Address> addresses;

	private TextView titleLabel;

	private Button addAddressButton;
	private ListView addressListView;
	private AddressAdapter addressAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		initData();
		initUI();
	}
	
	private void initData(){
		addresses=new ArrayList<Address>();
		addresses.add(new Address(1, "张三", "13916161616", "上海市浦东新区软件园一期", "306号"));
		addresses.add(new Address(2, "李四", "13916161616", "上海市浦东新区软件园一期", "307号"));
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("服务地址");

		addAddressButton = (Button) findViewById(R.id.btn_add_address);
		addressListView = (ListView) findViewById(R.id.listView_address);
		addressAdapter = new AddressAdapter(addresses, AddressActivity.this);
		addressListView.setAdapter(addressAdapter);
		
		addAddressButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_add_address:
			Intent intent=new Intent(getApplicationContext(), AddressConfigActivity.class);
			startActivityForResult(intent, ADDRESS_CONFIG);
			break;
		}
	}

}
