package com.naming.peoplehelp.util;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtil
{

	private static ProgressDialog progressDialog;

	public static void showWaitDialog(Context context, String message)
	{
		if (!((Activity) context).isFinishing())
		{
			progressDialog = new ProgressDialog(context);
			// progressDialog.setTitle(title);
			progressDialog.setMessage(message);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
	}

	public static void updateWaitDialogMessage(String message)
	{
		if (null != progressDialog)
		{
			progressDialog.setMessage(message);
		}
	}

	public static void hideWaitDialog()
	{
		if (null != progressDialog && progressDialog.isShowing())
		{
			progressDialog.dismiss();
		}
	}

	public static void releaseWaitDialog()
	{
		progressDialog = null;
	}


}
