package com.naming.peoplehelp.fragment;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.naming.peoplehelp.R;
import com.naming.peoplehelp.adapter.MyFragmentPagerAdapter;

public class LifeFragment extends BaseFragment {

	Resources resources;

	private ViewPager lifeViewPager;
	private ArrayList<Fragment> fragmentsList;
	private TextView tvLifeTab1, tvLifeTab2, tvLifeTab3, tvLifeTab4,
			tvLifeTab5;

	private int currIndex = 0;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one;
	public final static int num = 5;
	
	Fragment lifeFragment1;
    Fragment lifeFragment2;
    Fragment lifeFragment3;
    Fragment lifeFragment4;
    Fragment lifeFragment5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_life, container, false);
		resources = getResources();
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(position_one,
				offset, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(300);
		
		return view;
	}

	private void InitTextView(View parentView) {

		tvLifeTab1 = (TextView) parentView.findViewById(R.id.tv_tab_1);
		tvLifeTab2 = (TextView) parentView.findViewById(R.id.tv_tab_2);
		tvLifeTab3 = (TextView) parentView.findViewById(R.id.tv_tab_3);
		tvLifeTab4 = (TextView) parentView.findViewById(R.id.tv_tab_4);
		tvLifeTab5 = (TextView) parentView.findViewById(R.id.tv_tab_5);

		tvLifeTab1.setTextColor(resources.getColor(R.color.blue_bg));
        tvLifeTab1.setBackgroundResource(R.drawable.tab_selected_bg);
        
		tvLifeTab1.setOnClickListener(new MyOnClickListener(0));
		tvLifeTab2.setOnClickListener(new MyOnClickListener(1));
		tvLifeTab3.setOnClickListener(new MyOnClickListener(2));
		tvLifeTab4.setOnClickListener(new MyOnClickListener(3));
		tvLifeTab5.setOnClickListener(new MyOnClickListener(4));
		
	}
	
	 private void InitViewPager(View parentView) {
	        lifeViewPager = (ViewPager) parentView.findViewById(R.id.viewPager_life);
	        fragmentsList = new ArrayList<Fragment>();

	        lifeFragment1 = new LifeFragment_1();
	        lifeFragment2 = new LifeFragment_1();
	        lifeFragment3 = new LifeFragment_1();
	        lifeFragment4 = new LifeFragment_1();
	        lifeFragment5 = new LifeFragment_1();

	        fragmentsList.add(lifeFragment1);
	        fragmentsList.add(lifeFragment2);
	        fragmentsList.add(lifeFragment3);
	        fragmentsList.add(lifeFragment4);
	        fragmentsList.add(lifeFragment5);
	        
	        lifeViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
	        lifeViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	        lifeViewPager.setCurrentItem(0);
	        
	    }

	private void InitWidth(View parentView) {

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 5);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			lifeViewPager.setCurrentItem(index);
		}
	};
	
	public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab2.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab2.setBackgroundResource(R.color.transparent);
                } else if (currIndex == 2) {
                	animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab3.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab3.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab4.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab4.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 4) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab5.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab5.setBackgroundResource(R.color.transparent);
				}
                tvLifeTab1.setTextColor(resources.getColor(R.color.blue_bg));
                tvLifeTab1.setBackgroundResource(R.drawable.tab_selected_bg);
                break;
            case 1:
            	if (currIndex == 0) {
                    animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab1.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab1.setBackgroundResource(R.color.transparent);
                } else if (currIndex == 2) {
                	animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab3.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab3.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab4.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab4.setBackgroundResource(R.color.transparent);
				}
				else if (currIndex == 4) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab5.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab5.setBackgroundResource(R.color.transparent);
				}
                tvLifeTab2.setTextColor(resources.getColor(R.color.blue_bg));
                tvLifeTab2.setBackgroundResource(R.drawable.tab_selected_bg);
                break;
            case 2:
            	if (currIndex == 0) {
                    animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab1.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab1.setBackgroundResource(R.color.transparent);
                } else if (currIndex == 1) {
                	animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab2.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab2.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab4.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab4.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 4) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab5.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab5.setBackgroundResource(R.color.transparent);
				}
                tvLifeTab3.setTextColor(resources.getColor(R.color.blue_bg));
                tvLifeTab3.setBackgroundResource(R.drawable.tab_selected_bg);
                break;
            case 3:
            	if (currIndex == 0) {
                    animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab1.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab1.setBackgroundResource(R.color.transparent);
                } else if (currIndex == 1) {
                	animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab2.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab2.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 2) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab3.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab3.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 4) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab5.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab5.setBackgroundResource(R.color.transparent);
				}
                tvLifeTab4.setTextColor(resources.getColor(R.color.blue_bg));
                tvLifeTab4.setBackgroundResource(R.drawable.tab_selected_bg);
                break;
            case 4:
            	if (currIndex == 0) {
                    animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab1.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab1.setBackgroundResource(R.color.transparent);
                } else if (currIndex == 1) {
                	animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab2.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab2.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 2) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab3.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab3.setBackgroundResource(R.color.transparent);
				}else if (currIndex == 3) {
					animation = new TranslateAnimation(position_one, offset, 0, 0);
                    tvLifeTab4.setTextColor(resources.getColor(R.color.white));
                    tvLifeTab4.setBackgroundResource(R.color.transparent);
				}
            	tvLifeTab5.setTextColor(resources.getColor(R.color.blue_bg));
                tvLifeTab5.setBackgroundResource(R.drawable.tab_selected_bg);
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}
