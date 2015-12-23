package com.naming.peoplehelp.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.naming.peoplehelp.activity.application.ContextApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * ÿ�ο�ʼ��ʱ��ʱ����������timer, û�뵽�ð취�����²�
 * ע��Ѹ����onCreate()onDestroy()��activity��onCreate()onDestroy()ͬ������
 * 
 */
public class TimeButton extends Button implements OnClickListener {
	
	private long lenght = 60 * 1000;// ����ʱ����,�������Ĭ��60��
	private String textafter = "������»�ȡ";
	private String textbefore = "��ȡ��֤��";
	private final String TIME = "time";
	private final String CTIME = "ctime";
	private OnClickListener mOnclickListener;
	private Timer t;
	private TimerTask tt;
	private long time;
	Map<String, Long> map = new HashMap<String, Long>();

	public TimeButton(Context context) {
		super(context);
		setOnClickListener(this);

	}

	public TimeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnClickListener(this);
	}

	@SuppressLint("HandlerLeak")
	Handler han = new Handler() {
		public void handleMessage(android.os.Message msg) {
			TimeButton.this.setText(time / 1000 + textafter);
			time -= 1000;
			if (time < 0) {
				TimeButton.this.setEnabled(true);
				TimeButton.this.setText(textbefore);
				clearTimer();
			}
		};
	};

	private void initTimer() {
		time = lenght;
		t = new Timer();
		tt = new TimerTask() {

			@Override
			public void run() {
				han.sendEmptyMessage(0x01);
			}
		};
	}

	private void clearTimer() {
		if (tt != null) {
			tt.cancel();
			tt = null;
		}
		if (t != null)
			t.cancel();
		t = null;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		if (l instanceof TimeButton) {
			super.setOnClickListener(l);
		} else
			this.mOnclickListener = l;
	}

	@Override
	public void onClick(View v) {
		if (mOnclickListener != null)
			mOnclickListener.onClick(v);
		initTimer();
		this.setText(time / 1000 + textafter);
		this.setEnabled(false);
		t.schedule(tt, 0, 1000);
		// t.scheduleAtFixedRate(task, delay, period);
	}

	/**
	 * ��activity��onDestroy()����ͬ��
	 */
	public void onDestroy() {
		if (ContextApplication.validateTimeMap == null)
			ContextApplication.validateTimeMap = new HashMap<String, Long>();
		ContextApplication.validateTimeMap.put(TIME, time);
		ContextApplication.validateTimeMap.put(CTIME,
				System.currentTimeMillis());
		clearTimer();
	}

	/**
	 * ��activity��onCreate()����ͬ��
	 */
	public void onCreate(Bundle bundle) {
		
		if (ContextApplication.validateTimeMap == null)
			return;
		if (ContextApplication.validateTimeMap.size() <= 0)// �����ʾû���ϴ�δ��ɵļ�ʱ
			return;
		long time = System.currentTimeMillis()
				- ContextApplication.validateTimeMap.get(CTIME)
				- ContextApplication.validateTimeMap.get(TIME);
		ContextApplication.validateTimeMap.clear();
		if (time > 0)
			return;
		else {
			initTimer();
			this.time = Math.abs(time);
			t.schedule(tt, 0, 1000);
			this.setText(time + textafter);
			this.setEnabled(false);
		}
	}

	/** * ���ü�ʱʱ����ʾ���ı� */
	public TimeButton setTextAfter(String text1) {
		this.textafter = text1;
		return this;
	}

	/** * ���õ��֮ǰ���ı� */
	public TimeButton setTextBefore(String text0) {
		this.textbefore = text0;
		this.setText(textbefore);
		return this;
	}

	/**
	 * ���õ���ʱ����
	 * 
	 * @param lenght
	 *            ʱ�� Ĭ�Ϻ���
	 * @return
	 */
	public TimeButton setLenght(long lenght) {
		this.lenght = lenght;
		return this;
	}
	/*

*
*/
}