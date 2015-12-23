package com.naming.peoplehelp.activity;

import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

@SuppressLint("SimpleDateFormat")
public class BaseActivity extends Activity{
	
	public static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void doBack(View view) {
		onBackPressed();
	}

}
