package com.naming.peoplehelp.activity;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.util.ValidateUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CouponExchangeActivity extends BaseActivity implements
		OnClickListener {

	private TextView titleLabel;

	private EditText couponNumberText;
	private EditText couponPasswordText;
	private Button confirmExchangeButton;
	
	private String couponNumber;
	private String couponCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coupon_exchange);
		initUI();
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("帮友卡兑换");

		couponNumberText = (EditText) findViewById(R.id.text_coupon_number);
		couponPasswordText = (EditText) findViewById(R.id.text_coupon_code);
		confirmExchangeButton = (Button) findViewById(R.id.btn_confirm_exchange);

		confirmExchangeButton.setEnabled(false);
		doValidate();
		confirmExchangeButton.setOnClickListener(this);
	}

	// 自动校验
	private void doValidate() {
		couponNumberText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				couponNumber=couponNumberText.getText().toString().trim();
				couponCode=couponPasswordText.getText().toString().trim();
				if (ValidateUtil.validatePhone(couponNumber)&&ValidateUtil.validateSmsCode(couponCode)) {
					confirmExchangeButton.setEnabled(true);
				}else {
					confirmExchangeButton.setEnabled(false);
				}
			}
		});
		couponPasswordText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				couponNumber=couponNumberText.getText().toString().trim();
				couponCode=couponPasswordText.getText().toString().trim();
				if (ValidateUtil.validatePhone(couponNumber)&&ValidateUtil.validateSmsCode(couponCode)) {
					confirmExchangeButton.setEnabled(true);
				}else {
					confirmExchangeButton.setEnabled(false);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_confirm_exchange:
			
			break;
		default:
			break;
		}
	}

}
