package com.naming.peoplehelp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.entity.Address;
import com.naming.peoplehelp.view.dialog.AlertDialog;

public class AddressConfigActivity extends BaseActivity implements OnClickListener{
	
	private Address address;
	private String userName;
	private String phoneNumber;
	private String addressName;
	private String addressDetail;
	
	private TextView titleLabel;
	private TextView otherButton;
	private EditText userNameText;
	private EditText phoneNumberText;
	private EditText addressNameText;
	private EditText addressDetailText;
	private Button confirmButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_config);
		Intent intent = this.getIntent();
		address = (Address) intent.getSerializableExtra("address");
		initUI();
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		otherButton=(TextView) findViewById(R.id.btn_other);
		userNameText=(EditText) findViewById(R.id.text_user_name);
		phoneNumberText=(EditText) findViewById(R.id.text_phone_number);
		addressNameText=(EditText) findViewById(R.id.text_address_name);
		addressDetailText=(EditText) findViewById(R.id.text_address_detail);
		confirmButton=(Button) findViewById(R.id.btn_confirm);
		
		initData();
		confirmButton.setOnClickListener(this);
		otherButton.setOnClickListener(this);
	}
	
	private void initData(){
		
		if (address!=null) {
			titleLabel.setText("修改地址");
			otherButton.setText("删除");
			userName=address.getUserName();
			phoneNumber=address.getPhoneNumber();
			addressName=address.getAddressName();
			addressDetail=address.getAddressDetail();
			
			userNameText.setText(userName);
			phoneNumberText.setText(phoneNumber);
			addressNameText.setText(addressName);
			addressDetailText.setText(addressDetail);
		}else {
			titleLabel.setText("新增地址");
			otherButton.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm:
			
			break;
		case R.id.btn_other:
			showDialog("您确定要删除这条服务地址信息？");
			break;
		}
	}
	
	private void showDialog(String message) {
		new AlertDialog(AddressConfigActivity.this).builder().setTitle("提示")
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
