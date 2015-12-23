package com.naming.peoplehelp.activity;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;

import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends BaseActivity implements OnClickListener {

	private TextView titleLabel;

	private RelativeLayout userAgreementLayout;
	private RelativeLayout commonProblemLayout;
	private RelativeLayout versionUpdateLayout;
	private RelativeLayout aboutUsLayout;
	private TextView versionNameLabel;
	private Button quitLoginButton;
	
	private String versionName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initUI();
	}

	private String getVersion() {

		try {
			PackageManager manager = getPackageManager();
			PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
			String versionName = info.versionName;
			return versionName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	private void initUI() {

		titleLabel = (TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("∏¸∂‡…Ë÷√");

		userAgreementLayout = (RelativeLayout) findViewById(R.id.layout_user_agreement);
		commonProblemLayout = (RelativeLayout) findViewById(R.id.layout_common_problem);
		versionUpdateLayout = (RelativeLayout) findViewById(R.id.layout_version_update);
		aboutUsLayout = (RelativeLayout) findViewById(R.id.layout_about_us);
		versionNameLabel = (TextView) findViewById(R.id.label_version_name);
		quitLoginButton = (Button) findViewById(R.id.btn_quit_login);

		versionName=getVersion();
		versionNameLabel.setText("V"+versionName);
		if (ContextApplication.hasLogin) {
			quitLoginButton.setVisibility(View.VISIBLE);
		} else {
			quitLoginButton.setVisibility(View.GONE);
		}
		userAgreementLayout.setOnClickListener(this);
		commonProblemLayout.setOnClickListener(this);
		versionUpdateLayout.setOnClickListener(this);
		aboutUsLayout.setOnClickListener(this);
		quitLoginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_user_agreement:

			break;
		case R.id.layout_common_problem:

			break;
		case R.id.layout_version_update:

			break;
		case R.id.layout_about_us:

			break;
		case R.id.btn_quit_login:
			saveMinePhone();
			ContextApplication.hasLogin=false;
			finish();
			break;
		}
	}
	
	private void saveMinePhone(){
		Editor editor = ContextApplication.sp.edit();
		editor.putString("minePhone", "");
		editor.putBoolean("hasLogin", false);
		editor.commit();
	}
}
