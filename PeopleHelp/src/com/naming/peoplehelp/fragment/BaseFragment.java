package com.naming.peoplehelp.fragment;

import java.lang.reflect.Field;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.naming.peoplehelp.activity.LoginActivity;

public class BaseFragment extends Fragment{
	
	public static final int LOGIN_CODE = 1;
	
	protected void redirtToLogin(){
		Intent loginIntent=new Intent(getActivity(), LoginActivity.class);
		startActivityForResult(loginIntent, LOGIN_CODE);
	}
	
	// 设置对话框自动关闭机制
	protected void setAutoDismiss(DialogInterface dialog, boolean isAutoDismiss){
		try
		{
			Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			// 设置mShowing值，欺骗android系统
			field.set(dialog, isAutoDismiss);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected Dialog.OnClickListener cancelBtnListener = new Dialog.OnClickListener()
	{
		@Override
		public void onClick(DialogInterface dialog, int arg1)
		{
			setAutoDismiss(dialog, true);
		}
	};
	
	
}
