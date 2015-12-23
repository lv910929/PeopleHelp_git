package com.naming.peoplehelp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

import com.naming.peoplehelp.R;

public class SplashActivity extends Activity{
	
	private AlphaAnimation start_anima;
	
	View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_splash, null);
		setContentView(view);
		if (!checkNetWork()) {
			Toast.makeText(getApplicationContext(), "无网络可用", Toast.LENGTH_SHORT).show();
			redirectToNETWORK();
		}
		initView();
	}
	
	private void initView() {
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				redirectToMain();
			}
		});
	}
	
	private boolean checkNetWork(){
		
		boolean result=false;
		ConnectivityManager mConnectivity= (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);  
		 // 检查网络连接，如果无网络可用，就不需要进行连网操作等    
		 NetworkInfo info = mConnectivity.getActiveNetworkInfo();    
		 if (info == null ||!mConnectivity.getBackgroundDataSetting()) {  
			 result=false;
		 }else {
            NetworkInfo[] infos = mConnectivity.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < infos.length; i++) {   
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {   
                       result=true;  
                    }   
                }   
            } 
        }      
		return result;
	}
	
	private void redirectToNETWORK(){
		Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
		startActivityForResult(intent, 0);
		return;
	}
	
	private void redirectToMain(){
		if (checkNetWork()) {
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finish();
		}else {
			finish();
		}
	}

}
