package com.naming.peoplehelp.api;

import org.apache.http.message.BasicHeader;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naming.peoplehelp.activity.application.ContextApplication;
import com.naming.peoplehelp.util.ScreenSizeUtil;

import android.content.Context;

/**
 *  ����ӿڿ�����
 */
@SuppressWarnings("unused")
public class MyHttpAPIControl {
	
	public static final String MY_HTTP_HOME = "http://mapi.univs.cn/mobile/index.php";
	private static MyHttpAPIControl mInstance = null;
	public static AsyncHttpClient client = new AsyncHttpClient();

	private static final ContextApplication getApplication() {
		return ContextApplication.getInstance();
	}

	private MyHttpAPIControl() {
	}

	public static MyHttpAPIControl newInstance() {
		if (mInstance == null) {
			mInstance = new MyHttpAPIControl();
		}
		return mInstance;
	}

	/**
	 * @return ����ͷ
	 */
	private static BasicHeader[] getBaseHeader() {
		BasicHeader[] header = new BasicHeader[1];
		// header[0] = new BasicHeader("APP_KEY", Constant.API_APP_KEY);
		return header;
	}

	/**
	 * ���нӿ� ����ͨ�ò���
	 */
	private static RequestParams getBaseParams() {
		RequestParams params = new RequestParams();
		params.put("app", "mobile");
		params.put("type", "mobile");
		return params;
	}

	/**
	 * ���� get
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param handler
	 */
	private void get(String url, RequestParams params,
			AsyncHttpResponseHandler handler) {
		String urlWithQueryString = AsyncHttpClient.getUrlWithQueryString(url,
				params);
		client.get(getApplication(), url, null, params, handler);
	}

	/**
	 * ���� post
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param handler
	 */
	private void post(String url, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.post(getApplication(), url, null, params,
				"application/x-www-form-urlencoded", handler);
	}

	/**
	 * ���� put
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param handler
	 */
	private void put(String url, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.put(getApplication(), url, null, null,
				"application/x-www-form-urlencoded", handler);
	}

	/**
	 * ���� delete
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param handler
	 */
	private void delete(String url, RequestParams params,
			AsyncHttpResponseHandler handler) {
		client.delete(getApplication(), url, getBaseHeader(), params, handler);
	}

	/**
	 * 2.1 ����
	 * 
	 * @param handler
	 */
	public void getSplashInfo(Context context, AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("system_name", "android");
		baseParams.add("screen_width", ScreenSizeUtil.getScreenWidth(context)
				+ "");
		baseParams.add("screen_height", ScreenSizeUtil.getScreenHeight(context)
				+ "");
		baseParams.add("controller", "index");
		baseParams.add("action", "splash");
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.3.1 Ƶ���б�
	 * 
	 * @param handler
	 */
	public void getNewsCategory(AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "content");
		baseParams.add("action", "category");
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.3.2 �����б�
	 * 
	 * @param handler
	 */
	public void getNewsList(String catid, int page, long time,
			AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "content");
		baseParams.add("action", "index");
		baseParams.add("catid", catid);
		baseParams.add("page", page + "");
		baseParams.add("time", time + "");
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.3.3 �����б�õ�Ƭ
	 * 
	 * @param handler
	 */
	public void getNewsListSlide(String catid, long time,
			AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "content");
		baseParams.add("action", "slide");
		baseParams.add("catid", catid);
		baseParams.add("time", time + "");
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.4.1 ��������
	 * 
	 * @param handler
	 */
	public void getArticle(String contentid, AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "article");
		baseParams.add("action", "content");
		baseParams.add("contentid", contentid);
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.5.1 ��ͼ����
	 * 
	 * @param handler
	 */
	public void getPicture(String contentid, AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "picture");
		baseParams.add("action", "content");
		baseParams.add("contentid", contentid);
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.6.1 ר������
	 * 
	 * @param handler
	 */
	public void getSpecial(String contentid, AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "special");
		baseParams.add("action", "content");
		baseParams.add("contentid", contentid);
		get(MY_HTTP_HOME, baseParams, handler);
	}

	/**
	 * 2.7.1����ͳ��
	 * 
	 * @param handler
	 */
	public void getTongji(String contentid, AsyncHttpResponseHandler handler) {
		RequestParams baseParams = getBaseParams();
		baseParams.add("controller", "tongji");
		baseParams.add("action", "content");
		baseParams.add("contentid", contentid);
		get(MY_HTTP_HOME, baseParams, handler);
	}

}
