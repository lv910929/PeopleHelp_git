package com.naming.peoplehelp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.fragment.HomeFragment;
import com.naming.peoplehelp.fragment.LifeFragment;
import com.naming.peoplehelp.fragment.MineFragment;
import com.naming.peoplehelp.fragment.OrderFragment;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	private HomeFragment homeFragment;
	private OrderFragment orderFragment;
	private MineFragment mineFragment;
	//private LifeFragment lifeFragment;o

	private RelativeLayout homeTabButton;
	private ImageView homeTabImage;
	private TextView homeTabLabel;

	private RelativeLayout orderTabButton;
	private ImageView orderTabImage;
	private TextView orderTabLabel;
	
	/*private RelativeLayout lifeTabButton;
	private ImageView lifeTabImage;
	private TextView lifeTabLabel;*/

	private RelativeLayout mineTabButton;
	private ImageView mineTabImage;
	private TextView mineTabLabel;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		fragmentManager = getSupportFragmentManager();
		// ��һ������ʱѡ�е�0��tab
		setTabSelection(0);
	}

	private void initUI() {
		homeTabButton = (RelativeLayout) findViewById(R.id.btn_tab_home);
		homeTabImage = (ImageView) findViewById(R.id.image_tab_home);
		homeTabLabel = (TextView) findViewById(R.id.label_tab_home);

		orderTabButton = (RelativeLayout) findViewById(R.id.btn_tab_order);
		orderTabImage = (ImageView) findViewById(R.id.image_tab_order);
		orderTabLabel = (TextView) findViewById(R.id.label_tab_order);
		
		/*lifeTabButton = (RelativeLayout) findViewById(R.id.btn_tab_life);
		lifeTabImage = (ImageView) findViewById(R.id.image_tab_life);
		lifeTabLabel = (TextView) findViewById(R.id.label_tab_life);*/

		mineTabButton = (RelativeLayout) findViewById(R.id.btn_tab_mine);
		mineTabImage = (ImageView) findViewById(R.id.image_tab_mine);
		mineTabLabel = (TextView) findViewById(R.id.label_tab_mine);

		homeTabButton.setOnClickListener(this);
		orderTabButton.setOnClickListener(this);
		mineTabButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tab_home:
			setTabSelection(0);
			break;
		case R.id.btn_tab_order:
			setTabSelection(1);
			break;
		case R.id.btn_tab_mine:
			setTabSelection(2);
			break;
		}
	}

	/**
	 * 0��ʾ��ҳ��1��ʾ������2��ʾ���2��ʾ�ҵ�
	 */
	private void setTabSelection(int index) {
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();
		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);
		switch (index) {
		case 0:
			// ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ
			homeTabImage.setImageResource(R.drawable.icon_tabbar_home_pressed);
			homeTabLabel.setTextColor(getResources().getColor(R.color.blue_bg));
			if (homeFragment == null) {
				homeFragment = new HomeFragment();
				transaction.add(R.id.panel_content, homeFragment);
			} else {
				transaction.show(homeFragment);
			}
			break;
		case 1:
			orderTabImage
					.setImageResource(R.drawable.icon_tabbar_message_pressed);
			orderTabLabel
					.setTextColor(getResources().getColor(R.color.blue_bg));
			if (orderFragment == null) {
				orderFragment = new OrderFragment();
				transaction.add(R.id.panel_content, orderFragment);
			} else {
				transaction.show(orderFragment);
			}
			break;
		case 2:
			mineTabImage.setImageResource(R.drawable.icon_tabbar_mine_pressed);
			mineTabLabel.setTextColor(getResources().getColor(R.color.blue_bg));
			if (mineFragment == null) {
				mineFragment = new MineFragment();
				transaction.add(R.id.panel_content, mineFragment);
			} else {
				transaction.show(mineFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * ��������е�ѡ��״̬��
	 */
	private void clearSelection() {
		homeTabImage.setImageResource(R.drawable.icon_tabbar_home_normal);
		homeTabLabel.setTextColor(Color.parseColor("#82858b"));

		orderTabImage.setImageResource(R.drawable.icon_tabbar_message_normal);
		orderTabLabel.setTextColor(Color.parseColor("#82858b"));
		
		/*lifeTabImage.setImageResource(R.drawable.icon_tabbar_life_normal);
		lifeTabLabel.setTextColor(Color.parseColor("#82858b"));*/

		mineTabImage.setImageResource(R.drawable.icon_tabbar_mine_normal);
		mineTabLabel.setTextColor(Color.parseColor("#82858b"));
	}

	/**
	 * �����е�Fragment����Ϊ����״̬��
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (orderFragment != null) {
			transaction.hide(orderFragment);
		}
		/*if (lifeFragment !=null) {
			transaction.hide(lifeFragment);
		}*/
		if (mineFragment != null) {
			transaction.hide(mineFragment);
		}
	}

	/**
	 * ��д���ؼ�����¼�
	 */
	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!homeFragment.isVisible()) {
				setTabSelection(0);
			}else {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "�ٰ�һ���˳�", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
				}
			}
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
