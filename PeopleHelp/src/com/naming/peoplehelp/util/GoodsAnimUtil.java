package com.naming.peoplehelp.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.naming.peoplehelp.R;
import com.nineoldandroids.animation.Animator;

public abstract class GoodsAnimUtil {
	
	private static View view;
	private static Animation animation;
	/** ������ */
	private static ViewGroup anim_mask_layout;
	private static Activity mActivity;
	private static View mImgcar;
	private static OnEndAnimListener mEndAnimListener;

	/** �������֮��Ľӿ� */
	public interface OnEndAnimListener {
		void onEndAnim();
	}

	public static void setOnEndAnimListener(OnEndAnimListener listenr) {
		mEndAnimListener = listenr;
	}

	public static void setAnim(Activity activity, View imgphoto, View imgcar) {
		mActivity = activity;
		mImgcar = imgcar;
		// һ���������飬�����洢��ť������Ļ��X��Y����
		int[] start_location = new int[2];
		// ���ǻ�ȡ����ť������Ļ��X��Y���꣨��Ҳ�Ƕ�����ʼ�����꣩
		imgphoto.getLocationInWindow(start_location);
		int[] start_location1 = new int[] { start_location[0],
				start_location[1] };
		// buyImg�Ƕ�����ͼƬ���ҵ���һ��С��R.drawable.sign��
		ImageView buyImg = new ImageView(mActivity);
		// ����buyImg��ͼƬ
		buyImg.setImageResource(R.drawable.aii);
		// ��ʼִ�ж���
		startAnim(buyImg, start_location1);
	}

	/**
	 *��ʼ����
	 */
	private static void startAnim(final View v, int[] start_location) {
	    anim_mask_layout = null;
	    anim_mask_layout = createAnimLayout();
	    anim_mask_layout.addView(v);//�Ѷ���С����ӵ�������
	    view = addViewToAnimLayout(anim_mask_layout, v,start_location);
	    int[] end_location = new int[2];// ���������洢��������λ�õ�X��Y����
	    mImgcar.getLocationInWindow(end_location);// shopCart���Ǹ����ﳵ
	    int width = getWindowsWidth(mActivity);
	    // ����λ��
	    int endY = end_location[1] - start_location[1];// ����λ�Ƶ�y����
	    int endX = 0 - start_location[0] + (mImgcar.getWidth() / 2);// ����λ�Ƶ�X����
	    TranslateAnimation translateAnimationX = new TranslateAnimation(0,endX, 0, 0);
	    translateAnimationX.setInterpolator(new LinearInterpolator());
	    translateAnimationX.setRepeatCount(0);// �����ظ�ִ�еĴ���
	    translateAnimationX.setFillAfter(true);
	    TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,0, endY);
	    translateAnimationY.setInterpolator(new AccelerateInterpolator());
	    translateAnimationY.setRepeatCount(0);// �����ظ�ִ�еĴ���
	    translateAnimationX.setFillAfter(true);
	    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.3f, 1.0f, 0.3f);
	    scaleAnimation.setInterpolator(new AccelerateInterpolator());
	    scaleAnimation.setRepeatCount(0);// �����ظ�ִ�еĴ���
	    scaleAnimation.setFillAfter(true);
	    scaleAnimation.setDuration(300);
	    final AnimationSet set = new AnimationSet(false);
	    set.setFillAfter(false);
	    set.addAnimation(translateAnimationY);
	    set.addAnimation(translateAnimationX);
	    //set.setStartOffset(300);
	    set.setDuration(800);// ������ִ��ʱ��
	    view.startAnimation(set);
	    // ���������¼�
	    set.setAnimationListener(new Animation.AnimationListener() {
	        // �����Ŀ�ʼ
	        @Override
	        public void onAnimationStart(Animation animation) {
	            v.setVisibility(View.VISIBLE);
	        }
	        @Override
	        public void onAnimationRepeat(Animation animation) {}
	        // �����Ľ���
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            v.setVisibility(View.GONE);
	            anim_mask_layout.removeAllViews();
	            YoYo.with(Techniques.Bounce).withListener(new Animator.AnimatorListener() {
	                @Override
	                public void onAnimationStart(Animator animation) {}
	                @Override
	                public void onAnimationEnd(Animator animation) {
	                    mEndAnimListener.onEndAnim();
	                }
	                @Override
	                public void onAnimationCancel(Animator animation) {}
	                @Override
	                public void onAnimationRepeat(Animator animation) {}
	            }).interpolate(new BounceInterpolator()).duration(400).playOn(mImgcar);
	        }
	    });
	}

	/**
	 * @param
	 * @return void
	 * @throws
	 * @Description: ����������
	 */
	private static ViewGroup createAnimLayout() {
		ViewGroup rootView = (ViewGroup) mActivity.getWindow().getDecorView();
		LinearLayout animLayout = new LinearLayout(mActivity);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setId(Integer.MAX_VALUE);
		animLayout.setBackgroundResource(android.R.color.transparent);
		rootView.addView(animLayout);
		return animLayout;
	}

	private static View addViewToAnimLayout(final ViewGroup vg,
			final View view, int[] location) {
		int x = location[0];
		int y = location[1];
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = x;
		lp.topMargin = y;
		view.setLayoutParams(lp);
		return view;
	}

	/**
	 * ��ȡ��Ļ�Ŀ��
	 */
	public final static int getWindowsWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
}
