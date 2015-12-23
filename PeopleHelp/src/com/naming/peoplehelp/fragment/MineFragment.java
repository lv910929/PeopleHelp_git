package com.naming.peoplehelp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.activity.AddressActivity;
import com.naming.peoplehelp.activity.CouponExchangeActivity;
import com.naming.peoplehelp.activity.FeedbackActivity;
import com.naming.peoplehelp.activity.SettingActivity;
import com.naming.peoplehelp.activity.VIPRechargeActivity;
import com.naming.peoplehelp.activity.application.ContextApplication;

public class MineFragment extends BaseFragment implements OnClickListener {

	private ImageView remindButton;
	private RelativeLayout mineInfoLayout;
	private RelativeLayout mineBalanceLayout;
	private RelativeLayout mineCouponLayout;
	private TextView minePhoneLabel;
	private TextView mineBalanceLabel;
	private TextView mineCouponLabel;

	private RelativeLayout vipRechangeLayout;
	private RelativeLayout couponExchangeLayout;
	private RelativeLayout mineAddressLayout;
	private RelativeLayout writeFeedbackLayout;
	private RelativeLayout moreSettingLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initUI();
	}

	private void initUI() {

		remindButton = (ImageView) getActivity()
				.findViewById(R.id.image_remind);
		mineInfoLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_mine_info);
		mineBalanceLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_mine_balance);
		minePhoneLabel = (TextView) getActivity().findViewById(
				R.id.label_user_name);
		mineCouponLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_mine_coupon);
		mineBalanceLabel = (TextView) getActivity().findViewById(
				R.id.label_mine_balance);
		mineCouponLabel = (TextView) getActivity().findViewById(
				R.id.label_mine_coupon);
		vipRechangeLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_vip_recharge);
		couponExchangeLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_coupon_exchange);
		mineAddressLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_mine_address);
		writeFeedbackLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_write_feedback);
		moreSettingLayout = (RelativeLayout) getActivity().findViewById(
				R.id.layout_more_setting);

		initData();
		remindButton.setOnClickListener(this);
		mineInfoLayout.setOnClickListener(this);
		mineBalanceLayout.setOnClickListener(this);
		mineCouponLayout.setOnClickListener(this);
		vipRechangeLayout.setOnClickListener(this);
		couponExchangeLayout.setOnClickListener(this);
		mineAddressLayout.setOnClickListener(this);
		writeFeedbackLayout.setOnClickListener(this);
		moreSettingLayout.setOnClickListener(this);
	}

	private void initData() {

		if (ContextApplication.hasLogin) {
			minePhoneLabel.setText(ContextApplication.minePhone);
			mineBalanceLabel.setText("0.00 Ôª");
			mineCouponLabel.setText("0 ¸ö");
		} else {
			minePhoneLabel.setText("µÇÂ¼/×¢²á");
			mineBalanceLabel.setText("--");
			mineCouponLabel.setText("--");
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.image_remind:
			if (ContextApplication.hasLogin) {

			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_mine_info:
			if (ContextApplication.hasLogin) {

			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_mine_balance:
			if (ContextApplication.hasLogin) {

			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_mine_coupon:
			if (ContextApplication.hasLogin) {

			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_vip_recharge:
			if (ContextApplication.hasLogin) {
				redirectToVIP();
			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_coupon_exchange:
			if (ContextApplication.hasLogin) {
				redirectToExchange();
			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_mine_address:
			if (ContextApplication.hasLogin) {
				startActivity(new Intent(getActivity(), AddressActivity.class));
			} else {
				redirtToLogin();
			}
			break;
		case R.id.layout_write_feedback:
			redirectToFeedback();
			break;
		case R.id.layout_more_setting:
			startActivity(new Intent(getActivity(), SettingActivity.class));
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BaseFragment.LOGIN_CODE
				&& resultCode == getActivity().RESULT_OK) {
			initData();
		}
	}
	
	private void redirectToVIP(){
		Intent vipIntent=new Intent(getActivity(), VIPRechargeActivity.class);
		startActivity(vipIntent);
	}
	
	private void redirectToExchange(){
		Intent exchangeIntent=new Intent(getActivity(), CouponExchangeActivity.class);
		startActivity(exchangeIntent);
	}

	private void redirectToFeedback() {
		Intent feedbackIntent = new Intent(getActivity(),
				FeedbackActivity.class);
		startActivity(feedbackIntent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		initData();
	}

}
