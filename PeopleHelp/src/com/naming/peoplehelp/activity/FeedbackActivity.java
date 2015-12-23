package com.naming.peoplehelp.activity;

import com.naming.peoplehelp.R;

import android.os.Bundle;
import android.widget.TextView;


public class FeedbackActivity extends BaseActivity{
	
	private TextView titleLabel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		initUI();
	}
	
	private void initUI(){
		
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("Òâ¼û·´À¡");
	}

}
