package com.naming.peoplehelp.activity;

import com.naming.peoplehelp.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HouseReclameActivity extends BaseActivity implements OnClickListener{
	
	private TextView titleLabel;
	
	private RelativeLayout serviceTimeLayout;
	private RelativeLayout userInfoLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_house_reclame);
		initUI();
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("ÐÂ·¿¿ª»Ä");
		
		serviceTimeLayout=(RelativeLayout) findViewById(R.id.layout_service_time);
		userInfoLayout=(RelativeLayout) findViewById(R.id.layout_user_info);
		
		serviceTimeLayout.setOnClickListener(this);
		userInfoLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_service_time:
			
			break;
		case R.id.layout_user_info:
			
			break;
		default:
			break;
		}
	}
}
