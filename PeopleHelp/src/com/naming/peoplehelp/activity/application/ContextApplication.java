package com.naming.peoplehelp.activity.application;

import java.io.File;
import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class ContextApplication extends Application {
	
	public static final String SERVICE_TEL = "400-000-8888";

	private static ContextApplication instance;
	
	public static ContextApplication getInstance() {
		return instance;
	}

	public static SharedPreferences sp;
	//用于判断是否登录
	public static boolean hasLogin;
	
	public static String minePhone;
	// 用于存放倒计时时间
	public static Map<String, Long> validateTimeMap;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		sp = instance.getSharedPreferences("config", Context.MODE_PRIVATE);
		hasLogin = ContextApplication.sp.getBoolean("hasLogin", false);
		minePhone = sp.getString("minePhone", "");
	}

	public File getExternalStorageDirectory() {
		File externalStorageDirectory = Environment
				.getExternalStorageDirectory();
		int sdk = Integer.valueOf(Build.VERSION.SDK);
		if (sdk >= 8 && externalStorageDirectory == null) {
			externalStorageDirectory = getExternalFilesDir(null);
		}
		if (externalStorageDirectory != null) {
			externalStorageDirectory = new File(
					externalStorageDirectory.getAbsolutePath(),
					Constants.APP_FOLDER);
			if (!externalStorageDirectory.exists()) {
				if (!externalStorageDirectory.mkdirs()) {
					externalStorageDirectory = null;
				}
			}
		}
		return externalStorageDirectory;
	}

	private void initImageLoad() {
		File externalStorageDirectory = getExternalStorageDirectory();
		if (externalStorageDirectory != null) {
			externalStorageDirectory = new File(
					externalStorageDirectory.getAbsolutePath(), "imageCach");
			if (!externalStorageDirectory.exists()) {
				if (!externalStorageDirectory.mkdirs()) {
					externalStorageDirectory = null;
				}
			}
		}
		Builder config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 1)
				.memoryCache(new LruMemoryCache(10 * 1024 * 1024))
				.memoryCacheSize(10 * 1024 * 1024)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO);
		if (externalStorageDirectory != null) {
			config.discCache(new UnlimitedDiscCache(externalStorageDirectory));
		}
		ImageLoaderConfiguration build = config.build();
		ImageLoader.getInstance().init(build);
	}

	public File getCachDateeDir() {
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (!sdCardExist) {
			return getCacheDir();
		}
		File externalStorageDirectory = getExternalStorageDirectory();
		File cachDateeDir = new File(
				externalStorageDirectory.getAbsoluteFile(),
				Constants.APP_CACH_DATA_FOLDER);
		if (!cachDateeDir.exists()) {
			if (!cachDateeDir.mkdirs()) {
				cachDateeDir = null;
			}
		}
		return cachDateeDir;
	}
}
