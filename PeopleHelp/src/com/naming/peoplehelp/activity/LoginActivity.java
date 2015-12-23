package com.naming.peoplehelp.activity;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.util.ValidateUtil;
import com.naming.peoplehelp.view.TimeButton;
import com.naming.peoplehelp.view.dialog.AlertDialog;

public class LoginActivity extends BaseActivity implements OnClickListener {
	
	public static final String HAS_LOGIN="has_login";

	private TextView titleLabel;

	private EditText minePhoneText;
	private EditText validateCodeText;
	private TimeButton getValidateButton;
	private Button loginButton;
	private TextView agreementLabel;
	private TextView noSmsCodeLabel;
	private LinearLayout callServiceLayout;
	private Button callServiceButton;

	private String minePhone;
	private String validateCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initUI();
		getValidateButton.onCreate(savedInstanceState);
		getValidateButton.setTextAfter("秒后重新获取").setTextBefore("获取验证码").setLenght(60 * 1000);
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("快速登录");

		minePhoneText = (EditText) findViewById(R.id.text_mine_phone);
		validateCodeText = (EditText) findViewById(R.id.text_validate_code);
		getValidateButton = (TimeButton) findViewById(R.id.btn_get_validate);
		loginButton = (Button) findViewById(R.id.btn_quick_login);
		agreementLabel = (TextView) findViewById(R.id.label_to_agreement);
		noSmsCodeLabel = (TextView) findViewById(R.id.label_no_validate);
		callServiceLayout = (LinearLayout) findViewById(R.id.layout_call_service);
		callServiceButton = (Button) findViewById(R.id.btn_call_service);

		getValidateButton.setEnabled(false);
		loginButton.setEnabled(false);
		doValidate();
		getValidateButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		agreementLabel.setOnClickListener(this);
		noSmsCodeLabel.setOnClickListener(this);
		callServiceButton.setOnClickListener(this);
	}

	// 自动校验
	private void doValidate() {
		minePhoneText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				minePhone = minePhoneText.getText().toString().trim();
				validateCode = validateCodeText.getText().toString().trim();
				if (ValidateUtil.validatePhone(minePhone)) {
					getValidateButton.setEnabled(true);
				} else {
					getValidateButton.setEnabled(false);
				}
				if (ValidateUtil.validatePhone(minePhone)
						&& ValidateUtil.validateSmsCode(validateCode)) {
					loginButton.setEnabled(true);
				} else {
					loginButton.setEnabled(false);
				}
			}
		});
		validateCodeText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				minePhone = minePhoneText.getText().toString().trim();
				validateCode = validateCodeText.getText().toString().trim();
				if (ValidateUtil.validatePhone(minePhone)
						&& ValidateUtil.validateSmsCode(validateCode)) {
					loginButton.setEnabled(true);
				} else {
					loginButton.setEnabled(false);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_get_validate:

			break;
		case R.id.btn_quick_login:
			ContextApplication.hasLogin=true;
			ContextApplication.minePhone=minePhone;
			saveMinePhone();
			sendResult();
			break;
		case R.id.label_to_agreement:

			break;
		case R.id.label_no_validate:
			callServiceLayout.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_call_service:
			showDialog("拨打客服电话：400-000-8888");
			break;
		}
	}

	private void showDialog(String message) {
		new AlertDialog(LoginActivity.this).builder().setTitle("联系客服")
				.setCancelable(true).setMsg(message)
				.setPositiveButton("确定", new OnClickListener() {
					@Override
					public void onClick(View v) {
						callService();
					}
				}).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {
					}
				}).show();
	}

	private void callService() {
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ ContextApplication.SERVICE_TEL));
		this.startActivity(intent);// 开始意图(及拨打电话)
	}
	
	private void saveMinePhone(){
		Editor editor = ContextApplication.sp.edit();
		editor.putString("minePhone", minePhone);
		editor.putBoolean("hasLogin", true);
		editor.commit();
	}
	
	private void sendResult(){
		
		Intent intent=new Intent();
		intent.putExtra(HAS_LOGIN, true);
        setResult(RESULT_OK, intent);
        finish();
	}
	
	@Override
	protected void onDestroy() {
		getValidateButton.onDestroy();
		super.onDestroy();
	}

}
