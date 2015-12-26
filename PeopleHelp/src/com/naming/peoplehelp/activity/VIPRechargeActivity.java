package com.naming.peoplehelp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.view.CustomNestRadioGroup;
import com.naming.peoplehelp.view.CustomNestRadioGroup.OnCheckedChangeListener;
import com.naming.peoplehelp.view.dialog.AlertDialog;

public class VIPRechargeActivity extends BaseActivity implements OnClickListener,OnCheckedChangeListener{
	
	private TextView titleLabel;
	private TextView callServiceButton;
	
	private TextView userNameLabel;
	
	private CustomNestRadioGroup vipRadioGroup;
	private CheckBox vip1CheckBox;
	private CheckBox vip2CheckBox;
	private CheckBox vip3CheckBox;
	private CheckBox vip4CheckBox;
	private CheckBox vip5CheckBox;
	private Button rechargeButton;
	
	private Integer rechargeMoney;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vip_recharge);
		initUI();
	}
	
	private void initUI(){
		titleLabel=(TextView) findViewById(R.id.label_actionbar);
		titleLabel.setText("会员充值");
		callServiceButton=(TextView) findViewById(R.id.btn_other);
		callServiceButton.setText("客服");
		
		userNameLabel=(TextView) findViewById(R.id.label_user_name);
		vipRadioGroup=(CustomNestRadioGroup) findViewById(R.id.radioGroup_vip);
		vip1CheckBox=(CheckBox) findViewById(R.id.cb_vip_1);
		vip2CheckBox=(CheckBox) findViewById(R.id.cb_vip_2);
		vip3CheckBox=(CheckBox) findViewById(R.id.cb_vip_3);
		vip4CheckBox=(CheckBox) findViewById(R.id.cb_vip_4);
		vip5CheckBox=(CheckBox) findViewById(R.id.cb_vip_5);
		
		rechargeButton=(Button) findViewById(R.id.btn_recharge_vip);
		
		userNameLabel.setText(ContextApplication.minePhone);
		vip1CheckBox.setChecked(true);
		rechargeMoney=500;
		
		callServiceButton.setOnClickListener(this);
		if (vipRadioGroup != null){
			vipRadioGroup.setOnCheckedChangeListener(this);
		}
		rechargeButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_other:
			showDialog("拨打客服电话：400-000-8888");
			break;
		case R.id.btn_recharge_vip:
			if (vip1CheckBox.isChecked()||vip2CheckBox.isChecked()||vip3CheckBox.isChecked()||vip4CheckBox.isChecked()||vip5CheckBox.isChecked()) {
				Toast.makeText(getApplicationContext(),"充值"+rechargeMoney, Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(getApplicationContext(),"请选择充值金额", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CustomNestRadioGroup group, int checkedId) {
		group.check(checkedId);
		if (group.getId() == R.id.radioGroup_vip) {
			switch (checkedId) {
			case R.id.cb_vip_1:
				rechargeMoney=500;
				break;
			case R.id.cb_vip_2:
				rechargeMoney=1000;
				break;
			case R.id.cb_vip_3:
				rechargeMoney=2000;
				break;
			case R.id.cb_vip_4:
				rechargeMoney=3000;
				break;
			case R.id.cb_vip_5:
				rechargeMoney=5000;
				break;
			default:
				break;
			}
			
		}
	}
	
	private void showDialog(String message) {
		new AlertDialog(VIPRechargeActivity.this).builder().setTitle("联系客服")
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

}
